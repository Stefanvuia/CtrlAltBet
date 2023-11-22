package view;
import java.awt.*;

public class GridBagUtils {
    private Container container;

    public GridBagUtils(Container container) {
        this.container = container;
    }

    public void addComponentWithConstraints(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = GridBagConstraints.BOTH;

        container.add(component, gbc);
    }
}
