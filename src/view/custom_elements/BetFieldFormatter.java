package view.custom_elements;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class BetFieldFormatter extends NumberFormatter {
    public BetFieldFormatter(NumberFormat numberFormat) {
        super(numberFormat);
        this.setValueClass(Integer.class);
        this.setMinimum(0);
        this.setMaximum(Integer.MAX_VALUE);
        this.setAllowsInvalid(false);
        this.setCommitsOnValidEdit(true);
    }
}
