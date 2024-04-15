package cauzy.familytasklist.model.family;

import cauzy.familytasklist.model.enums.AgeGroup;

public final class FamilyMember extends People {

	private AgeGroup ageGroup;

	public FamilyMember(String name, Integer age) {
		super(name, age);
		ageGroupSelection(age);
	}
	
	@Override
	public String toString() {
		return id + " - " + " Name: " + name + ", Age: " + age + ", AgeGroup: " + ageGroup;
	}

	private void ageGroupSelection(Integer age) {

		if (age < 14) {
			setAgeGroup(AgeGroup.CHILD);
		} else if (age < 18) {
			setAgeGroup(AgeGroup.TEEN);
//			setAgeGroup(AgeGroup.valueOf("TEEN"));
		} else {
			setAgeGroup(AgeGroup.ADULT);
		}

	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}
	
	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}
	
//	public void setAgeGroup(String ageGroup) {
//		this.ageGroup = AgeGroup.valueOf(ageGroup);
//	}
	
}
