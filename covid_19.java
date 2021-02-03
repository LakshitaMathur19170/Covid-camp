package Refresher;

import java.util.ArrayList;
import java.util.Scanner;

public class covid_19 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		ArrayList<patient> details = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			patient obj = new patient(scn.next(), scn.nextFloat(), scn.nextInt(), scn.nextInt());
			obj.setId(i);
			details.add(obj);
		}
		camp camp = new camp(details);

		while (camp.admittednum != n) {
			int querynum = scn.nextInt();
			if (querynum == 1) {
				camp.query1();
			} else if (querynum == 2) {
				camp.query2();
			} else if (querynum == 3) {
				camp.query3();
			} else if (querynum == 4) {
				camp.query4();
			} else if (querynum == 5) {
				camp.query5();
			} else if (querynum == 6) {
				camp.query6();
			} else if (querynum == 7) {
				camp.query7();
			} else if (querynum == 8) {
				camp.query8();
			} else {
				camp.query9();
			}
		}


	}
}

class patient {
	private String Name;
	private float Temperature;
	private int oxygen;
	private int age;
	private int id;
	private String HealthCentre;
	private int recoverydays;
	private boolean admitted;

	public patient(String Name, float Temperature, int oxygen, int age) {
		this.age = age;
		this.Name = Name;
		this.oxygen = oxygen;
		this.Temperature = Temperature;
		admitted = false;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getTemperature() {
		return Temperature;
	}

	public void setTemperature(float temperature) {
		Temperature = temperature;
	}

	public int getOxygen() {
		return oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHealthCentre() {
		return HealthCentre;
	}

	public void setHealthCentre(String healthCentre) {
		HealthCentre = healthCentre;
	}

	public int getRecoverydays() {
		return recoverydays;
	}

	public void setRecoverydays(int recoverydays) {
		this.recoverydays = recoverydays;
	}

	public boolean isAdmitted() {
		return admitted;
	}

	public void setAdmitted(boolean admitted) {
		this.admitted = admitted;
	}

}

class healthcenter {
	private String Name;
	private int numofbeds;
	private float temperatureCriteria;
	private int oxygenlevel;
	private String Status;
	private ArrayList<patient> patientname = new ArrayList<>();
	private int counterofbeds = 0;

	public healthcenter(String Name, float temperatureCriteria, int oxygenlevel, int numofbeds) {
		this.Name = Name;
		this.numofbeds = numofbeds;
		this.oxygenlevel = oxygenlevel;
		this.temperatureCriteria = temperatureCriteria;

	}

	public void addpatient(patient obj) {
		patientname.add(obj);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getNumofbeds() {
		return numofbeds;
	}

	public void setNumofbeds(int numofbeds) {
		this.numofbeds = numofbeds;
	}

	public float getTemperatureCriteria() {
		return temperatureCriteria;
	}

	public void setTemperatureCriteria(float temperatureCriteria) {
		this.temperatureCriteria = temperatureCriteria;
	}

	public int getOxygenlevel() {
		return oxygenlevel;
	}

	public void setOxygenlevel(int oxygenlevel) {
		this.oxygenlevel = oxygenlevel;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public ArrayList<patient> getPatientname() {
		return patientname;
	}

	public void setPatientname(ArrayList<patient> patientname) {
		this.patientname = patientname;
	}

	public int getCounterofbeds() {
		return counterofbeds;
	}

	public void setCounterofbeds(int counterofbeds) {
		this.counterofbeds = counterofbeds;
	}

}

class camp {
	int admittednum = 0;
	Scanner scn = new Scanner(System.in);
	ArrayList<healthcenter> insitutes = new ArrayList<>();
	ArrayList<patient> details;

	public camp(ArrayList<patient> details) {
		this.details = details;

	}

	public void query1() {
		String str = scn.next();
		System.out.print("Temperature Criteria- ");
		float tempc = scn.nextFloat();
		System.out.print("Oxygen Level- ");
		int oxy = scn.nextInt();
		System.out.print("Number of Available Beds- ");
		int beds = scn.nextInt();
		healthcenter obj = new healthcenter(str, tempc, oxy, beds);

		for (int i = 0; i < details.size(); i++) {
			if (!details.get(i).isAdmitted()) {
				if (details.get(i).getOxygen() >= obj.getOxygenlevel()) {
					System.out.print("Recovery days for admitted patient ID " + details.get(i).getId() + " ");
					details.get(i).setRecoverydays(scn.nextInt());
					details.get(i).setHealthCentre(str);
					;
					details.get(i).setAdmitted(true);
					obj.addpatient(details.get(i));
					obj.setCounterofbeds(obj.getCounterofbeds() + 1);
					admittednum++;
					if (obj.getCounterofbeds() == obj.getNumofbeds()) {
						break;
					}

				}
			}
		}
		if (obj.getCounterofbeds() != obj.getNumofbeds()) {
			for (int i = 0; i < details.size(); i++) {
				if (!details.get(i).isAdmitted()) {
					if (details.get(i).getTemperature() <= obj.getTemperatureCriteria()) {
						System.out.print("Recovery days for admitted patient ID " + details.get(i).getId() + " ");
						details.get(i).setRecoverydays(scn.nextInt());
						details.get(i).setHealthCentre(str);
						details.get(i).setAdmitted(true);
						obj.addpatient(details.get(i));
						obj.setCounterofbeds(obj.getCounterofbeds() + 1);
						admittednum++;
						if (obj.getCounterofbeds() == obj.getNumofbeds()) {
							break;
						}
					}
				}
			}
		}
		if (obj.getCounterofbeds() == obj.getNumofbeds()) {
			obj.setStatus("CLOSED");
		}
		insitutes.add(obj);
	}

	public void query2() {
		System.out.println("Account ID removed of admitted patients");
		for (int i = 0; i < details.size(); i++) {
			if (details.get(i).isAdmitted()) {
				System.out.println(details.get(i).getId());
				details.listIterator(i);
			}
		}
	}

	public void query3() {
	
		for (int i = 0; i < insitutes.size(); i++) {
			if (insitutes.get(i).getStatus().equals("CLOSED")) {
				System.out.println(insitutes.get(i).getName());
				insitutes.listIterator(i);
			}
		}

	}

	public void query4() {
		int cnt = 0;
		for (int i = 0; i < details.size(); i++) {
			if (!details.get(i).isAdmitted()) {
				cnt++;
			}
		}

		System.out.println(cnt + " patients");

	}

	// query4? remove patients
	public void query5() {
		int cnt = 0;
		for (int i = 0; i < insitutes.size(); i++) {
			if (insitutes.get(i).getStatus().equals("OPEN")) {
				cnt++;
			}
		}
		System.out.println(cnt + " institutes are admitting patients currently");
	}

	public void query6() {
		String str = scn.next();
		for (int i = 0; i < insitutes.size(); i++) {
			if (insitutes.get(i).getName().equals(str)) {
				System.out.println(str);
				System.out.println("Temperature should be <=" + insitutes.get(i).getTemperatureCriteria());
				System.out.println("Oxygen levels should be >=" + insitutes.get(i).getOxygenlevel());
				System.out.println("Number of Available beds –" + insitutes.get(i).getNumofbeds());
				System.out.println("Admission Status – " + insitutes.get(i).getStatus());
			} else {
				System.out.println("Invalid");
			}
		}
	}

	public void query7() {
		int num = scn.nextInt();
		for (int i = 0; i < details.size(); i++) {

			if (details.get(i).getId() == num) {
				System.out.println(details.get(i).getId());
				System.out.println(details.get(i).getName());
				System.out.println("Temperature- " + details.get(i).getTemperature());
				System.out.println("Oxygen level- " + details.get(i).getOxygen());
				if (details.get(i).isAdmitted()) {
					System.out.println("Admitting status- Admitted");
					System.out.println("Admitted Insitute- " + details.get(i).getHealthCentre());
				} else {
					System.out.println("Admitting status- Not Admitted");
					System.out.println("Admitted Insitute- Nil");
				}
			}
		}
	}

	public void query8() {
		System.out.println();
		for (int i = 0; i < details.size(); i++) {
			System.out.println(details.get(i).getId() + " " + details.get(i).getName());
		}
	}

	public void query9() {
		String str = scn.next();
		for (int i = 0; i < insitutes.size(); i++) {
			if (insitutes.get(i).getName().equals(str)) {
				for (int j = 0; j < insitutes.get(i).getPatientname().size(); j++) {
					System.out.println(insitutes.get(i).getPatientname().get(j).getName() + ", recovery time is"
							+ insitutes.get(i).getPatientname().get(j).getRecoverydays() + " days");
				}
			} else {
				System.out.println("Invalid");
			}
		}
	}

}
