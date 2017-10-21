import  java.util.*;

/**
 * Exercise solutions to Assignment: Reading Web Logs
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/cAl9o/programming-exercise-reading-log-files 
 *
 * Modified By Chase Hennion
 * @version 2017-10-20
 */
public class LogEntry
{
     private String ipAddress;
     private Date accessTime;
     private String request;
     private int statusCode;
     private int bytesReturned;
     
   public LogEntry(String ip, Date time, String req, int status, int bytes) {
       ipAddress = ip;
       accessTime = time;
       request = req;
       statusCode = status;
       bytesReturned = bytes;
       
   }
   
   public String getIpAddress() {
         return ipAddress;
    }
    public Date getAccessTime() {
         return accessTime;
   }   
   public String getRequest() {
         return request;
   }
   public int getStatusCode() {
         return statusCode;
   }
   public int getBytesReturned() {
         return bytesReturned;
   }
   
   public String toString() {
       return ipAddress + " " + accessTime + " " + request 
           + " " + statusCode + " " + bytesReturned;
    }
}
