package libedit.helpers;

@SuppressWarnings("serial")
public class IntegerField extends AbstractNumField<Integer> {

    public IntegerField(NumType type) {
        super(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerField(int min, int max) {
        super(min, max);
    }

    public void checkFormat() {
        String text = field.getText();
        boolean success = false;

        try {

            // Use just one decimal place
            int decimalIndex = text.indexOf(".");
            if (decimalIndex != -1 && text.length() > decimalIndex + 2) {
                text = text.substring(0, decimalIndex + 2);
            }

            // Try to convert to a integer
            int value = Integer.parseInt(text);

            // Keep the value within the allowable range
            if (value > maxVal)
                value = (int) maxVal;
            else if (value < minVal)
                value = (int) minVal;

            // Convert back to text
            text = Integer.toString(value);

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

    @Override
    public Integer getVal() {
        if (val != null) {
            return val;
        }
        else {
            return Integer.parseInt(this.getText());
        }
    }
}
