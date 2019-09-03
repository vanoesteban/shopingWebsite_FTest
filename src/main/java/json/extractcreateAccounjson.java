package json;

import org.json.simple.JSONObject;

/**
 * sample JSONObject Class
 */
public class extractcreateAccounjson {
    private String email, password, firstname, lastname, company, address, address2, city, zip, additionalinfo, phone, mobile, alias;


    //the constructor requires the JSONObject when instantiated
    public extractcreateAccounjson(JSONObject object){
        setEmail(object.get("email").toString());
        setPassword(object.get("password").toString());
        setFirstname(object.get("firstname").toString());
        setLastname(object.get("lastname").toString());
        setCompany(object.get("company").toString());
        setAddress(object.get("address").toString());
        setAddress2(object.get("address2").toString());
        setCity(object.get("city").toString());
        setZip(object.get("zip").toString());
        setAdditionalinfo(object.get("additionalinfo").toString());
        setPhone(object.get("phone").toString());
        setMobile(object.get("mobile").toString());
        setAlias(object.get("alias").toString());
    }




    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress2() {
        return address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

}
