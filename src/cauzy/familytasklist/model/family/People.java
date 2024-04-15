package cauzy.familytasklist.model.family;

import cauzy.familytasklist.util.FamilyPeople.MinAge;
import cauzy.familytasklist.util.FamilyPeople.StringSizeMin;

public abstract class People {
	
	private static Integer idCounter = 0;
	
	@StringSizeMin(3)
	protected String name;
	@MinAge(0)
	protected Integer age;
	protected Integer id;
	
	public People(String name, Integer age) {
		this.name = name;
		this.age = age;
		id = idCounter;
		idCounter ++;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
