package view.custom_elements;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class BetFieldFormatter extends NumberFormatter {
    public BetFieldFormatter(NumberFormat numberFormat, int max) {
        super(numberFormat);
        this.setValueClass(Integer.class);
        this.setMinimum(0);
        this.setMaximum(max);
        this.setAllowsInvalid(false);
        this.setCommitsOnValidEdit(true);
    }
}
