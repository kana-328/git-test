package sukkiri;


import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

interface RideExperience {
    
}


class RandomWrapper{
    public static double getRanDouble(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static boolean ranBoolean(){
        return new Random().nextBoolean();
    }
}

class Name{
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}

class BMI{
    private double heightM;
    private double weightKg;

    public BMI(double heightM, double weightKg){
        this.heightM = heightM;
        this.weightKg = weightKg;
    }

    public double getWeightKg(){
        return this.weightKg;
    }

    public double getValue(){
        return this.weightKg/(this.heightM*this.heightM);
    }

    public String toString(){
        return this.heightM + " meters, " + this.weightKg + "kg, BMI:" + this.getValue();
    }
}

class Animal{
    protected String species;
    protected BMI bmi;
    protected double lifeSpanDays;
    protected String biologicalSex;
    protected Date spawnTime;
    protected Date deathTime;
    protected int hungerPercent = 100;
    protected int sleepPercent = 100;

    public Animal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        this.species = species;
        this.bmi = new BMI(heightM, weightKg);
        this.lifeSpanDays = lifeSpanDays;
        this.biologicalSex = biologicalSex;
        this.spawnTime = new java.util.Date();
    }

    public void eat(){
        if(!this.isAlive()) return;
        this.hungerPercent = 0;
    }

    public void setAsHungry(){
        if(!this.isAlive()) return;
        this.hungerPercent = 100;
    }

    public boolean isHungry(){
        return this.hungerPercent >= 70;
    }

    public void sleep(){
        if(!this.isAlive()) return;
        this.sleepPercent = 0;
    }

    public void setAsSleepy(){
        if(!this.isAlive()) return;
        this.sleepPercent = 100;
    }

    public boolean isSleepy(){
        return this.sleepPercent >= 70;
    }

    public void die(){
        this.sleepPercent = 0;
        this.hungerPercent = 0;
        this.deathTime = new java.util.Date();
    }

    public boolean isAlive(){
        return this.deathTime == null;
    }

    public String toString(){
        return this.species + " " + this.bmi + " lives " + this.lifeSpanDays + " days/" + "gender:" + this.biologicalSex + "." + this.status();
    }

    public String status(){
        return this.species + " status:" + " Hunger - " + this.hungerPercent + "%, " + "sleepiness:"+this.sleepPercent + "%" + ", Alive - " + this.isAlive() + ". First created at " + this.dateCreated();
    }

    public String dateCreated(){
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(this.spawnTime);
    }
}

class Mammal extends Animal{
    private double bodyTemperatureC;
    private double avgBodyTemperatureC;

    public Mammal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double avgBodyTemperatureC){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);

        this.avgBodyTemperatureC = avgBodyTemperatureC;
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public void eat(){
        super.eat();
        System.out.println("this " + this.species + " is eating with its single lower jaw");
    }

    public String toString(){
        return super.toString() + " " + this.mammalInformation();
    }

    public void increaseBodyHeat(double celcius){
        this.bodyTemperatureC+=celcius;
    }

    public void decreaseBodyHeat(double celcius){
        this.bodyTemperatureC-=celcius;
    }

    public void adjustBodyHeat(){
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public String mammalInformation(){
        return "This is a mammal with a temperature of: "+this.bodyTemperatureC;
    }
}

class Person extends Mammal{
    public static final String SPECIES = "Human";
    public static final double LIFE_EXPECTANCY = 30000;
    public static final double BODY_TEMPERATURE = 36;

    private Name name;
    private int age;

    public Person(String firstName, String lastName, int age, double heightM, double weightKg, String biologicalSex){
        super(Person.SPECIES, heightM, weightKg, Person.LIFE_EXPECTANCY, biologicalSex, Person.BODY_TEMPERATURE);
        this.name = new Name(firstName, lastName);
        this.age = age;
    }

    public String getName(){
        return this.name.toString();
    }

    public String toString(){
        return super.toString() + ". The name of this Person is " + this.getName();
    }
}



interface PlayfulPet{
    abstract public String play();
    abstract public String playWithPerson(Person person);
    abstract public String playNoise();
    abstract public String getPetName();
    abstract public double getRentalCosts();
    abstract public boolean likesActivity(String activity);
    abstract public boolean dislikesActivity(String activity);
    abstract public String doActivity(String activity);
    
}

class Cat extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Cat";
    public static final double LIFE_EXPECTANCY = 5500;
    public static final double BODY_TEMPERATURE = 37;

    private static final double PLAYFUL_HOURLY_COSTS = 50;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","groom","drink","crawl","explore","pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Cat(double heightM, double weightKg, String biologicalSex){
        super(Cat.SPECIES, heightM, weightKg, Cat.LIFE_EXPECTANCY, biologicalSex, Cat.BODY_TEMPERATURE);
    }

    public String toString(){
        return super.toString() + " this is a cat";
    }

    public void meow(){
        System.out.println("Meooow");
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This cat starts rolling on the floor, and pretends to play predator";
    }

    public String playWithPerson(Person person){
        String s = "The cat stares at " + person.getName();
        s+= ". After taking kin to " + person.getName() + ", " + person.getName() + " throws a mouse toy at this cat and the cat starts chasing the mouse toy";
        return s;
    }

    public String playNoise(){
        return "Meow";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The cat enjoyed eating food.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The cat took a good nap.";
        }
        else if(this.likesActivity(activity)) return "Meow. The cat really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "The cat really hated the " + activity + " activity.";
        return "The cat felt indiferent about the " + activity + " activity.";
    };
}

abstract class PlayfulPetAssistant{
    protected static final double DEFAULT_RENT_TIME = 1.0;
    protected static final String DEFAULT_TOUR = "all-rounder pack";

    protected Person currentPerson;
    protected double currentRentTime = PlayfulPetAssistant.DEFAULT_RENT_TIME;
    protected static String[] availableActivities = {"eat","walk","drink","nap","pet","run","explore"};
    protected static String[] availableTours = {"all-rounder pack", "deluxe rounder pack"};


   // abstract Map<String, PlayfulPetAssistant> getPetfulAssistant();

    public String[] getActivities(){
        return this.availableActivities;
    }

    public String[] getAvailableTours(){
        return this.availableTours;
    }

    public boolean isValidTour(String tour){
        return Arrays.asList(this.getAvailableTours()).contains(tour);
    }

    protected String getRandomActivity(){
        List<String> activities = Arrays.asList(this.getActivities());
        int ran = new Random().nextInt(activities.size());
        return activities.get(ran);
    }

    public void setPerson(Person person){
        this.currentPerson = person;
    }

    public void setHours(double hours){
        this.currentRentTime = hours;
    }

    public double getHours() {
        return this.currentRentTime;
    }

    public double getCurrentRentTime(){
        return this.currentRentTime;
    }

    public void reset(){
        this.currentPerson = null;
        this.currentRentTime = this.DEFAULT_RENT_TIME;
    }

    public double runAssistanceTour(Person person){
        return this.runAssistanceTour(person, this.DEFAULT_TOUR);
    }

    public double runAssistanceTour(Person person, String tour){
        if(!this.isValidTour(tour)) System.out.println("The tour guide does not accept the " + tour + " tour.");
        
        PlayfulPet playfulPet = this.createPlayfulPet();

        System.out.println("");
        System.out.println("Booting up... Playful Pet Assistance robot at your service.");
        System.out.println("Printing information about the Person to service..." + person);
        System.out.println("");
        System.out.println("Printing information about the Playful Pet - " + playfulPet.getPetName() + " to service..." + playfulPet);

        if(tour == "all-rounder pack" || tour == "deluxe rounder pack"){
            int count = tour == "all-rounder pack" ? 1 : 3;
            this.genericRounderTour(count, person, playfulPet);
        }
        else{
            System.out.println("The tour assistant robot for " + playfulPet.getPetName() + " and " + person.getName() + " did nothing.");
        }

        double rentalCosts = playfulPet.getRentalCosts() * this.getCurrentRentTime();

        this.reset();

        return rentalCosts;
    }

    public double runAssistanceTour(Person person, String tour, int amount){
        if(!this.isValidTour(tour)) System.out.println("The tour guide does not accept the " + tour + " tour.");
        
        PlayfulPet playfulPet = this.createPlayfulPet();

        System.out.println("");
        System.out.println("Booting up... Playful Pet Assistance robot at your service.");
        System.out.println("Printing information about the Person to service..." + person);
        System.out.println("");
        System.out.println("Printing information about the Playful Pet - " + playfulPet.getPetName() + " to service..." + playfulPet);

        if(tour == "all-rounder pack" || tour == "deluxe rounder pack"){
            int count = tour == "all-rounder pack" ? 1 : 3;
            this.genericRounderTour(count, person, playfulPet);
        }
        else{
            System.out.println("The tour assistant robot for " + playfulPet.getPetName() + " and " + person.getName() + " did nothing.");
        }

        double rentalCosts = playfulPet.getRentalCosts() * this.getCurrentRentTime() * amount;

        this.reset();

        return rentalCosts;
    }

    private void genericRounderTour(int activityCount, Person person, PlayfulPet pet){
        String newLine = System.lineSeparator();
        System.out.println(newLine + "Now starting the generic rounder tour with " + activityCount + " activity(s)");
        System.out.println(person.getName() + " greets " + pet.getPetName() + newLine);
        System.out.println(pet.play() + newLine);
        System.out.println(pet.playNoise() + newLine);
        System.out.println(pet.playWithPerson(person) + newLine);
        for(int i = 0; i < activityCount; i++){
            String activity = this.getRandomActivity();
            System.out.println(pet.getPetName() + " will now " + activity);
            System.out.println("--------");
            System.out.println(pet.doActivity(activity));
            System.out.println("--------" + newLine);
        }
    }

    public abstract PlayfulPet createPlayfulPet();

    public abstract List<PlayfulPet> createPlayfulPets(int amount);

}


class Invoice {
    public String title;
    public String discription;
    public int retCount;
    public String tour;
    public PlayfulPetAssistant playfulPetAssistant;
    public double cost;
    //public Invoice(String title, String discription, int retCount, String tour, double cost, PlayfulPetAssistant playfulPetAssistant) {
    //    this.title = title;
    //    // 続く
    //}
    private void setTitle(String title) {
        this.title = title;
    }

    private String getTitle() {
        return this.title;
    }
}

class FairyWorld{

    final int DEFAULT_AMOUNT = 1;
    final int MAX_AMOUNT = 3;
    private final Map<String, PlayfulPetAssistant> petAssistantMap = new HashMap<String, PlayfulPetAssistant>() {{
        put("Dog" ,new PlayfulDogAssistant());
        put("Rabbit" ,new PlayfulRabbitAssistant());
        put("Pony" ,new PlayfulPonyAssistant());
        put("Hamster" ,new PlayfulHamsterAssistant());
        put("Chicken" ,new PlayfulChickenAssistant());
        put("Hamster" ,new PlayfulHamsterAssistant());
        put("Goat" ,new PlayfulGoatAssistant());
        put("Cat" ,new PlayfulGoatAssistant());
    }};

    public void rentPet(PlayfulPetAssistant assistant, Person person){
        PlayfulPet playfulPet = assistant.createPlayfulPet();
        rentPet(playfulPet.getPetName(), person, DEFAULT_AMOUNT, PlayfulPetAssistant.DEFAULT_TOUR);


        System.out.println("bbbbbbbbbbb" + playfulPet.toString());
        System.out.println("Thank you for your pet rental!");
        double costs = assistant.runAssistanceTour(person);
        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
    }

    public void addPlayfulPetAssistant(String key, PlayfulPetAssistant assisitant) {
        petAssistantMap.put(key, assisitant);
    }

    public void rentPet(String petKey, Person p, int amount, String tour) {

        if (amount > MAX_AMOUNT) {
            System.out.println("規定額を超えています。");
            return;
        }

        PlayfulPetAssistant assistant = this.getPetfulAssistant(petKey);
        if (assistant == null) {
            System.out.println("PETがいませんでした。");
            return;
        }
        System.out.println("Thank you for your pet rental!");
        double costs = assistant.runAssistanceTour(p, tour);
        System.out.println(costs + " dollars were charged to " + p.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
    }

    // ペットがレンタルされるたびに生成される請求書は、タイトル、説明、総費用を含み、どのペットが何匹レンタルされたかが記載
    private Invoice getRentedPetsInvoice() {
        Invoice invoice = new Invoice();
        return invoice;
    }

    private PlayfulPetAssistant getPetfulAssistant(String petKey) {
        for (Map.Entry<String, PlayfulPetAssistant> entry : this.petAssistantMap.entrySet()) {
            System.out.println(entry);
            if (petKey.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}

// これでシステムが整ったので、あとは作成したい個々のPlayfulPetにクラスを追加して、Factory Methodを含むクライアントシステムをサブクラス化するだけです。今回のケースでは、PlayfulPetAssistantになります。
// PlayfulPetであるDogと、PlayfulDogAssistant（Factory MethodがDogを生成します）作成してください。
class Dog extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Dog";
    public static final double LIFE_EXPECTANCY = 4800;
    public static final double Body_TEMPERATURE = 39;
    
    private static final double PLAYFUL_HOURLY_COSTS = 35;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","chase","swim","drink","run","explore","pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"hug","dressup"};

    public Dog(double heightM, double weightKg, String biologicalSex){
        super(Dog.SPECIES, heightM, weightKg, Dog.LIFE_EXPECTANCY, biologicalSex, Dog.Body_TEMPERATURE);
    }

    public String toString(){
        return super.toString() + " this is a dog";
    }

    public void woof(){
        System.out.println("Woof Woof");
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This dog starts running on the park and chases a ball.";
    }

    public String playWithPerson(Person person){
        String s = "The dog runs towards " + person.getName();
        s+= ". After the dog taking kin to " + person.getName() + ", " + person.getName() + " throws a frisbee disk and the dog chases it.";
        return s;
    }

    public String playNoise(){
        return "Wooooof Woooof";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The dog ate the entire food in a few bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The dog took a quick nap.";
        }
        else if(this.likesActivity(activity)) return "Woof Woof. The dog really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "The dog did not like " + activity + " activity. The dog walked away";
        return "The dog felt indiferent about the " + activity + " activity.";
    };
}

// PlayfulPetであるRabbitと、PlayfulRabbitAssistant（Factory MethodがRabbitを生成します）作成してください。
class Rabbit extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Rabbit";
    public static final double LIFE_EXPECTANCY = 3000;
    public static final double Body_TEMPERATURE = 40;
    
    private static final double PLAYFUL_HOURLY_COSTS = 30;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","chase","drink","jump","dig"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","dressup"};

    public Rabbit(double heightM, double weightKg, String biologicalSex){
        super(Rabbit.SPECIES, heightM, weightKg, Rabbit.LIFE_EXPECTANCY, biologicalSex, Rabbit.Body_TEMPERATURE);
    }

    public String toString(){
        return super.toString() + " this is a rabbit";
    }

    public void woof(){
        System.out.println("Squeak Squeak");
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This rabbit starts jumping around and chases an insect on the grass.";
    }

    public String playWithPerson(Person person){
        String s = "The bunny hops towards " + person.getName();
        s+= ". After the rabbit stares at " + person.getName() + ", " + person.getName() + " makes the rabbit chase a small carrot. The rabbit hops towards it.";
        return s;
    }

    public String playNoise(){
        return "Squeak";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The rabbit chew on the food bit by bit taking tiny bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The rabbit took a long nap.";
        }
        else if(this.likesActivity(activity)) return ".... The Rabbit really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "Squeeeak. The Rabbit did not like " + activity + " activity. The rabbit quickly hopped away";
        return "The rabbit felt indiferent about the " + activity + " activity.";
    };
}

class Pony extends Mammal implements PlayfulPet {
	public static final String SPECIES = "Pony";
    public static final double LIFE_EXPECTANCY = 5000;
    public static final double Body_TEMPERATURE = 38;
    
    private static final double PLAYFUL_HOURLY_COSTS = 100;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","drink","run","contact"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","backhome"};
	public Pony(double heightM, double weightKg, String biologicalSex) {
		super(Pony.SPECIES, heightM, weightKg, Pony.LIFE_EXPECTANCY, biologicalSex, Pony.Body_TEMPERATURE);
	}

    public String getPetName(){
        return this.species;
    }

	public String play(){
        return "This pony starts running around park.";
    }

    public String playWithPerson(Person person){
        String s = "The pony hops towards " + person.getName();
        s+= ". After the pony stares at " + person.getName() + ", " + person.getName() + " makes the rabbit chase a small carrot. The rabbit hops towards it.";
        return s;
    }

    public String playNoise(){
        return "Hihiin";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The pony chew on the food bit by bit taking tiny bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The rabbit took a long nap.";
        }
        else if(this.likesActivity(activity)) return ".... The pony really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "Squeeeak. The pony did not like " + activity + " activity. The pony quickly hopped away";
        return "The pony felt indiferent about the " + activity + " activity.";
    };

}

class Hamster extends Mammal implements PlayfulPet {
	public static final String SPECIES = "Hamster";
    public static final double LIFE_EXPECTANCY = 200;
    public static final double Body_TEMPERATURE = 35;
    
    private static final double PLAYFUL_HOURLY_COSTS = 50;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","drink","run","ride"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","dakko"};
	public Hamster(double heightM, double weightKg, String biologicalSex) {
		super(Hamster.SPECIES, heightM, weightKg, Hamster.LIFE_EXPECTANCY, biologicalSex, Hamster.Body_TEMPERATURE);
	}

    public String getPetName(){
        return this.species;
    }

	public String play(){
        return "This master starts running around park.";
    }

    public String playWithPerson(Person person){
        String s = "The hamster hops towards " + person.getName();
        s+= ". After the hamster stares at " + person.getName() + ", " + person.getName() + " makes the rabbit chase a small carrot. The rabbit hops towards it.";
        return s;
    }

    public String playNoise(){
        return "hmhm";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The pony chew on the food bit by bit taking tiny bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The hamster took a long nap.";
        }
        else if(this.likesActivity(activity)) return ".... The hamster really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "Squeeeak. The hamster did not like " + activity + " activity. The hamster quickly hopped away";
        return "The hamster felt indiferent about the " + activity + " activity.";
    };

}

class Chicken extends Mammal implements PlayfulPet {
	public static final String SPECIES = "Hamster";
    public static final double LIFE_EXPECTANCY = 300;
    public static final double Body_TEMPERATURE = 36;
    
    private static final double PLAYFUL_HOURLY_COSTS = 70;
    private static final String[] LIKED_ACTIVITIES = {"eat","fly","run"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","dakko"};
	public Chicken(double heightM, double weightKg, String biologicalSex) {
		super(Chicken.SPECIES, heightM, weightKg, Hamster.LIFE_EXPECTANCY, biologicalSex, Hamster.Body_TEMPERATURE);
	}

    public String getPetName(){
        return this.species;
    }

	public String play(){
        return "This chicken starts running around park.";
    }

    public String playWithPerson(Person person){
        String s = "The chicken hops towards " + person.getName();
        s+= ". After the chicken stares at " + person.getName() + ", " + person.getName() + " makes the chicken chase a small carrot. The rabbit hops towards it.";
        return s;
    }

    public String playNoise(){
        return "kokekokko";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The chicken chew on the food bit by bit taking tiny bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The chicken took a long nap.";
        }
        else if(this.likesActivity(activity)) return ".... The chicken really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "Squeeeak. The chicken did not like " + activity + " activity. The hamster quickly hopped away";
        return "The chicken felt indiferent about the " + activity + " activity.";
    };

}

class Goat extends Mammal implements PlayfulPet {
	public static final String SPECIES = "goat";
    public static final double LIFE_EXPECTANCY = 300;
    public static final double Body_TEMPERATURE = 36;
    
    private static final double PLAYFUL_HOURLY_COSTS = 70;
    private static final String[] LIKED_ACTIVITIES = {"eat","sleep","run"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","ride"};
	public Goat(double heightM, double weightKg, String biologicalSex) {
		super(Chicken.SPECIES, heightM, weightKg, Hamster.LIFE_EXPECTANCY, biologicalSex, Hamster.Body_TEMPERATURE);
	}

    public String getPetName(){
        return this.species;
    }

	public String play(){
        return "This goat starts running around park.";
    }

    public String playWithPerson(Person person){
        String s = "The goat hops towards " + person.getName();
        s+= ". After the goat stares at " + person.getName() + ", " + person.getName() + " makes the goat chase a small carrot. The rabbit hops towards it.";
        return s;
    }

    public String playNoise(){
        return "meeeeeeee";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The goat chew on the food bit by bit taking tiny bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The goat took a long nap.";
        }
        else if(this.likesActivity(activity)) return ".... The goat really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "Squeeeak. The goat did not like " + activity + " activity. The hamster quickly hopped away";
        return "The goat felt indiferent about the " + activity + " activity.";
    };
}

// PlayfulDogAssistantとPlayfulRabbitAssistantは、サブクラス化することによって、PlayfulPetAssistantのFactory Methodを実装します。作成されるオブジェクトが異なる点以外は全く同じように動作します。


class PlayfulCatAssistant extends PlayfulPetAssistant{

    public PlayfulPet createPlayfulPet(){
        return new Cat(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female");
    }

    public List<PlayfulPet> createPlayfulPets(int amount){
        List<PlayfulPet> list = new ArrayList<PlayfulPet>();
        for (int i = 0; i <= amount; i++) {
            list.add(createPlayfulPet());
        }
        return list;
    }

    public Map<String, PlayfulPetAssistant> getPetfulAssistant() {
        Map<String, PlayfulPetAssistant> map = new HashMap<>();
        map.put("Cat", this);
        return map;
    }
}


class PlayfulDogAssistant extends PlayfulPetAssistant{
    public PlayfulPet createPlayfulPet(){
        return new Dog(RandomWrapper.getRanDouble(0.15,1.3), RandomWrapper.getRanDouble(9.5,25.8), RandomWrapper.ranBoolean() ? "male" : "female");
    }

    public List<PlayfulPet> createPlayfulPets(int amount){
        List<PlayfulPet> list = new ArrayList<PlayfulPet>();
        for (int i = 0; i <= amount; i++) {
            list.add(createPlayfulPet());
        }
        return list;
    }
}

class PlayfulRabbitAssistant extends PlayfulPetAssistant{
    public PlayfulPet createPlayfulPet(){
        return new Rabbit(RandomWrapper.getRanDouble(0.15,0.4), RandomWrapper.getRanDouble(2.2,10.2), RandomWrapper.ranBoolean() ? "male" : "female");
    }

    public List<PlayfulPet> createPlayfulPets(int amount){
        List<PlayfulPet> list = new ArrayList<PlayfulPet>();
        for (int i = 0; i <= amount; i++) {
            list.add(createPlayfulPet());
        }
        return list;
    }
}

class PlayfulPonyAssistant extends PlayfulPetAssistant{
	public PlayfulPet createPlayfulPet() {
		return new Pony(RandomWrapper.getRanDouble(1.15,2.4), RandomWrapper.getRanDouble(2.2,10.2), RandomWrapper.ranBoolean() ? "male" : "female");
	}

    public List<PlayfulPet> createPlayfulPets(int amount){
        List<PlayfulPet> list = new ArrayList<PlayfulPet>();
        for (int i = 0; i <= amount; i++) {
            list.add(createPlayfulPet());
        }
        return list;
    }
}

class PlayfulHamsterAssistant extends PlayfulPetAssistant{
	public PlayfulPet createPlayfulPet() {
		return new Hamster(RandomWrapper.getRanDouble(1.0,2.0), RandomWrapper.getRanDouble(0.2,2.2), RandomWrapper.ranBoolean() ? "male" : "female");
	}

    public List<PlayfulPet> createPlayfulPets(int amount){
        List<PlayfulPet> list = new ArrayList<PlayfulPet>();
        for (int i = 0; i <= amount; i++) {
            list.add(createPlayfulPet());
        }
        return list;
    }
}

class PlayfulChickenAssistant extends PlayfulPetAssistant{
    public PlayfulPet createPlayfulPet() {
        return new Chicken(RandomWrapper.getRanDouble(1.0,2.0), RandomWrapper.getRanDouble(0.2,2.2), RandomWrapper.ranBoolean() ? "male" : "female");
    }

    
    public List<PlayfulPet> createPlayfulPets(int amount){
        List<PlayfulPet> list = new ArrayList<PlayfulPet>();
        for (int i = 0; i <= amount; i++) {
            list.add(createPlayfulPet());
        }
        return list;
    }

}

class PlayfulGoatAssistant extends PlayfulPetAssistant{
    public PlayfulPet createPlayfulPet() {
        return new Goat(RandomWrapper.getRanDouble(50.0,80.0), RandomWrapper.getRanDouble(15.0,18.0), RandomWrapper.ranBoolean() ? "male" : "female");
    }

    public List<PlayfulPet> createPlayfulPets(int amount){
        List<PlayfulPet> list = new ArrayList<PlayfulPet>();
        for (int i = 0; i <= amount; i++) {
            list.add(createPlayfulPet());
        }
        return list;
    }


}

class Main{
    public static void main(String[] args){
        FairyWorld fairyWorld = new FairyWorld();
        Person jessica = new Person("Jessica", "Roller", 30, 1.65, 95, "female");



        //fairyWorld.rentPet(new PlayfulCatAssistant(), jessica);
        
        // その後、jessicaはdogやrabbitとも遊びます。サブクラス化してfactory methodを実装することで、異なるオブジェクトをサポートするようにコードを拡張しました。
        //fairyWorld.rentPet(new PlayfulDogAssistant(), jessica);
        //fairyWorld.rentPet(new PlayfulRabbitAssistant(), jessica);
		//fairyWorld.rentPet(new PlayfulPonyAssistant(), jessica);
		//fairyWorld.rentPet(new PlayfulHamsterAssistant(), jessica);
		//fairyWorld.rentPet(new PlayfulChickenAssistant(), jessica);
		//fairyWorld.rentPet(new PlayfulGoatAssistant(), jessica);
        fairyWorld.rentPet("Dog", jessica, 3, "test-tour");
    }
}