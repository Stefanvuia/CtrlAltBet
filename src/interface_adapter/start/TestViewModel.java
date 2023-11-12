package interface_adapter.start;

import interface_adapter.TestState;

public class TestViewModel {
    private TestState state = new TestState();

    public TestState getState() {
        return this.state;
    }
}
