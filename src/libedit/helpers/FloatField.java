package libedit.helpers;

@SuppressWarnings("serial")
public class FloatField extends AbstractNumField<Float> {

    private int places;

    public FloatField(int decimalPlaces) {
        super(-Float.MAX_VALUE, Float.MAX_VALUE);
        init(decimalPlaces);
    }

    public FloatField(int decimalPlaces, float min, float max) {
        super(min, max);
        init(decimalPlaces);
    }

    private void init(int decimalPlaces) {
        this.places = decimalPlaces;
    }

    public void checkFormat() {

        String text = field.getText();
        boolean success = false;

        try {
            text = formatPlaces(text);

            // Try to convert to a float
            float value = Float.parseFloat(text);

            // Keep the value within the allowable range
            if (value > maxVal)
                value = (float) maxVal;
            else if (value < minVal)
                value = (float) minVal;

            // Convert back to text
            text = Float.toString(value);
            text = formatPlaces(text);

            int decimalIndex = text.indexOf(".");

            // Trim if necessary
            if (decimalIndex == -1) {
                if (places != 0) {
                    text = text + ".0";
                }
            }
            else {
                if (places != 0) {
                    text = text.substring(0, decimalIndex + 1 + places);
                }
                else {
                    text = text.substring(0, decimalIndex);
                }
            }

            success = true;
        } catch (NumberFormatException e) {
            success = false;
        }

        if (success) {
            lastText = text;
            field.setText(text);
        }
        else {
            field.setText(lastText);
        }
    }

    /**
     * Enforces set number of decimal places on given text
     * 
     * @param floatStr
     *            The input float string to be formatted
     * @return The formatted float string
     */
    private String formatPlaces(String floatStr) {

        // Make sure there are enough characters to operate on
        int decimalIndex = floatStr.indexOf(".");
        int textPlaces = floatStr.length() - floatStr.indexOf(".") - 1;
        if (decimalIndex == -1) {
            floatStr += ".";
        }
        if (decimalIndex != -1 && floatStr.length() > decimalIndex + 1 + places) {
            floatStr = floatStr.substring(0, decimalIndex + 1 + places);
        }
        if (decimalIndex == 0) {
            floatStr = "0" + floatStr;
        }
        while (textPlaces < places) {
            floatStr += "0";
            textPlaces = floatStr.length() - floatStr.indexOf(".") - 1;
        }
        return floatStr;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
        this.setText(formatPlaces(this.getText()));
    }

    @Override
    public Float getVal() {
        if (val != null) {
            return val;
        }
        else {
            return Float.parseFloat(this.getText());
        }
    }
}
