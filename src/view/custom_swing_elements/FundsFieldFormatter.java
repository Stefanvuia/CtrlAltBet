package view.custom_swing_elements;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class FundsFieldFormatter extends NumberFormatter {
    public FundsFieldFormatter(NumberFormat format) {
        super(format);
        this.setValueClass(Integer.class);
        this.setMinimum(0);
        this.setMaximum(Integer.MAX_VALUE);
        this.setAllowsInvalid(false);
        this.setCommitsOnValidEdit(true);
    }
}
