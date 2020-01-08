package miniProject;

public class Reservation {

 private int tourNumber;
 private String sdate;
 private String fdate;
 private String country;
 private String airLine;
 private int price;
 
 @Override
 public String toString() {
  return "Reservation [tourNumber=" + tourNumber + ", sdate=" + sdate + ", fdate=" + fdate + ", country="
    + country + ", airLine=" + airLine + ", price=" + price + "]";
 }

 public int getTourNumber() {
  return tourNumber;
 }

 public void setTourNumber(int tourNumber) {
  this.tourNumber = tourNumber;
 }

 public String getSdate() {
  return sdate;
 }

 public void setSdate(String sdate) {
  this.sdate = sdate;
 }

 public String getFdate() {
  return fdate;
 }

 public void setFdate(String fdate) {
  this.fdate = fdate;
 }

 public String getCountry() {
  return country;
 }

 public void setCountry(String country) {
  this.country = country;
 }

 public String getAirLine() {
  return airLine;
 }

 public void setAirLine(String airLine) {
  this.airLine = airLine;
 }

 public int getPrice() {
  return price;
 }

 public void setPrice(int price) {
  this.price = price;
 }
 


}