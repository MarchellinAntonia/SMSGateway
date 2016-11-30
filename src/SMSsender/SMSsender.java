/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SMSsender;
import java.io.*; 
import java.util.*; 
import gnu.io.*; 
//import javax.comm.*;

/**
 *
 * @author chellin
 */
public class SMSsender { 
static Enumeration portList; 
static CommPortIdentifier portId; 
static String messageString1 = "AT";
//static String messageString2 = "AT+CPIN=\"7078\"";
static String messageString3 = "AT+CMGF=1"; 
static String messageString4 = "AT+CMGS=\"+628986663618\"";


static String messageString5 = "tes sms via java2";
static SerialPort serialPort;
static OutputStream outputStream;
static InputStream inputStream;
static char enter = 13;

static char CTRLZ = 26;
public static void main(String[] args) throws InterruptedException {
//    System.loadLibraryPath("rxtxSerial");
 portList = CommPortIdentifier.getPortIdentifiers();



while (portList.hasMoreElements()) {
    System.out.println("masuk while");

    portId = (CommPortIdentifier) portList.nextElement();
    if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
        System.out.println("masuk if atas");
        System.out.println("port id= "+portId.getName());

         if (portId.getName().contains("ttyUSB0")) {
             System.out.println("masuk if ttyUSB0");

            try {
                serialPort = (SerialPort)
                    portId.open("ttyUSB0", 2000);
            } catch (PortInUseException e) {System.out.println("err");}
            try {
                outputStream = serialPort.getOutputStream();
                inputStream = serialPort.getInputStream();
            } catch (IOException e) {e.printStackTrace();}
            try {
                serialPort.setSerialPortParams(115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE); 
            } catch (UnsupportedCommOperationException e) {e.printStackTrace();}
            try { 

                outputStream.write((messageString1 + enter).getBytes());


                Thread.sleep(100); 
                outputStream.flush();

//                outputStream.write((messageString2 + enter).getBytes()); 
//
//                 Thread.sleep(100); 
//                 outputStream.flush();

                outputStream.write((messageString3 + enter).getBytes());

                Thread.sleep(100); 
                outputStream.flush(); 

                outputStream.write((messageString4 + enter).getBytes()); 

                Thread.sleep(100);  
                outputStream.flush();

               outputStream.write((messageString5 + CTRLZ).getBytes());  

                outputStream.flush(); 
                Thread.sleep(100); 


    System.out.println("Wyslano wiadomosc");  
    Thread.sleep(3000);


    outputStream.close();
    serialPort.close();
    System.out.println("Port COM zamkniety"); 

            } catch (IOException e) {e.printStackTrace();}
        }
    }
}}}
