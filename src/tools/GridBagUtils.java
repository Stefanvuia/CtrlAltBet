package tools;

import java.awt.*;

/**
 * Utility class for simplifying the addition of components to a Container using GridBagLayout.
 */
public class GridBagUtils {

    /**
     * The Container to which components will be added using GridBagLayout.
     */
    private Container container;

    /**
     * Constructs a GridBagUtils object with the specified Container.
     *
     * @param container The Container to which components will be added using GridBagLayout.
     */
    public GridBagUtils(Container container) {
        this.container = container;
    }

    /**
     * Adds a component to the Container with specified constraints using GridBagLayout.
     *
     * @param component The component to be added.
     * @param gridx     The column at which the component will be placed.
     * @param gridy     The row at which the component will be placed.
     * @param gridwidth The number of columns the component will span.
     * @param gridheight The number of rows the component will span.
     * @param weightx   The horizontal weight of the component.
     * @param weighty   The vertical weight of the component.
     */
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
