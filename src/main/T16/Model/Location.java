package edu.csu2017fa314.T16.Model;

public class Location {
    private String Code;
    private String Name;

    public Location(String Code, String Name) {
        this.Code = Code;
        this.Name = Name;
    }

	public String getCode() {
		return Code;
	}

	public String getName() {
		return Name;
	}

    @Override
    public String toString() {
        return "Location{" +
                "code='" + Code + '\'' +
                ",name='" + Name + '\'' +
                '}';
    }
}
