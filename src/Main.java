import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class  Car{

    private String carId;
    private String brand;
    private String model;
    private boolean isAvailable;
    private double basePrice;

    Car(String carId,String brand,String model,double basePrice,boolean isAvailable){
        this.carId = carId;
        this.model = model;
        this.brand = brand;
        this.basePrice = basePrice;
        this.isAvailable = isAvailable;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isAvailable(){
     return isAvailable;
    }

    public double calculatePrice(int rentalDays){
        return basePrice * rentalDays;
    }


    public boolean rent(){
        return isAvailable = false;
    }

    public boolean returnCar(){
        return isAvailable = true;
    }
}

class Customer{

    private String customerId;
    private String customerName;

    Customer(String customerId,String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}

class Rental{

    private Car car;
    private Customer customer;
    private int days;

    public Rental(Customer customer, Car car, int days) {
        this.customer = customer;
        this.car = car;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }


}

class CarRentalSystem{

    private List<Car> cars;
    private List<Rental> rentals;
    private List<Customer> customers;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        rentals = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car,Customer customer,int days){
        if (car.isAvailable()){
            rentals.add(new Rental(customer,car,days));
            car.rent();
        }else {
            System.out.println("Car is not available for rent");
        }
    }

    public void returnCar(Car car){

        Rental rentalToRemove = null;
        for (Rental rent : rentals){
            if (rent.getCar() == car){
                rentalToRemove = rent;
                break;
            }
        }
        if (rentalToRemove != null){
            rentals.remove(rentalToRemove);
            car.returnCar();
        }else {
            System.out.println("Car was not rented");
        }
    }

    public void menu(){

        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("\n=== Rent A Car System ===\n");
            System.out.println("1. Rent A Car");
            System.out.println("2. Return A Car");
            System.out.println("3. Exit");
            System.out.println("Enter Your Choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){

                System.out.println("\nEnter Your Name : ");
                String customerName = scanner.nextLine();


                System.out.println("\nAvailable Cars");
                for (Car car : cars){
                    if(car.isAvailable()){
                        System.out.println(car.getCarId()+" -- "+car.getBrand()+" -- "+car.getModel());
                    }
                }
                System.out.println("\nEnter Your CarId : ");
                String carId = scanner.nextLine();;

                System.out.println("Enter Days To Rent : ");
                int days = scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer = new Customer("CUS"+(customers.size()+1),customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars){
                    if (car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null){
                    double totalPrice = selectedCar.calculatePrice(days);
                    System.out.println("\n==Rental Information==\n");
                    System.out.println("Customer ID : " + newCustomer.getCustomerId());
                    System.out.println("Customer Name : " + newCustomer.getCustomerName());
                    System.out.println("Customer Car : " + selectedCar.getModel()+"  "+selectedCar.getBrand());
                    System.out.println("Rent Days : " + days);
                    System.out.println("Total Price : " + totalPrice);

                    System.out.println("Confirm the Rent [Y/N]: ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar,newCustomer,days);
                        System.out.println("Car rented successfully");
                    }else {
                        System.out.println("Rental cancelled");
                    }
                }else {
                    System.out.println("Invalid car selection or car is not available for rent");
                }

            }
            else if (choice == 2){

                System.out.println("Enter CarId to return : ");
                String carId = scanner.nextLine();

                Car carAvailable = null;
                for (Car car : cars){
                    if(car.getCarId().equals(carId)){
                        carAvailable = car;
                        break;
                    }
                }
                if (carAvailable != null){

                    Customer customer = null;
                    for (Rental rental : rentals){
                        if (rental.getCar()==carAvailable){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer != null){
                        returnCar(carAvailable);
                        System.out.println("Car returned successfully");
                    }else{
                        System.out.println("Car is not returned or rental information is missing");
                    }
                }else{
                    System.out.println("Invalid car id or car is not rented");
                    }
                }
            else if (choice == 3){
                break;
            }else {
                System.out.println("Invalid choice.Please enter valid option");
            }
        }
        System.out.println("Thanks for Using the car rental system");
    }
}

public class Main {
    public static void main(String[] args) {

        CarRentalSystem carRentalSystem = new CarRentalSystem();

        Car car1 = new Car("c001","Honda","Civic",2000000,true);
        car1.setIsAvailable(true);

        Car car2 = new Car("c002","Toyota","Supra",10000000,true);
        car2.setIsAvailable(true);
//        Car car3 = new Car("c003","Mazda","RX8",1700000);
        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
//        carRentalSystem.addCar(car3);

        carRentalSystem.menu();
    }
}