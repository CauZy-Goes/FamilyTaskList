package cauzy.familytasklist.model.family;

import cauzy.familytasklist.util.FamilyPeople.MinAge;
import cauzy.familytasklist.util.FamilyPeople.StringSizeMin;

public abstract class People {
	
	@StringSizeMin
	protected String name;
	@MinAge
	protected Integer Age;
	
	public People(String name, Integer age) {
		this.name = name;
		Age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		Age = age;
	}
	
}
