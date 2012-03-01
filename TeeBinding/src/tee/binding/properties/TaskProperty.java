package tee.binding.properties;

import tee.binding.task.*;

public class TaskProperty<Owner> {
    public Task property;
    private Owner owner;
    public TaskProperty(Owner owner) {
	this.owner = owner;
    }
    public Owner is(Task it) {
	property = it;
	return owner;
    }
}
