package lesson09.model;

public class JsonShop {
    private String title;
    private boolean is_real;
    private AddressJson address;
    private StaffJson[] staff;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIs_real() {
        return is_real;
    }

    public void setIs_real(boolean is_real) {
        this.is_real = is_real;
    }

    public AddressJson getAddress() {
        return address;
    }

    public void setAddress(AddressJson address) {
        this.address = address;
    }

    public StaffJson[] getStaff() {
        return staff;
    }

    public void setStaff(StaffJson[] staff) {
        this.staff = staff;
    }
}
