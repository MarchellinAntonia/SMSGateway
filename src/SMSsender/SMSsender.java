/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SMSsender;

import java.io.*;
import java.util.*;
import gnu.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    static String selectedPort = "/dev/ttyUSB6";

    static String messageString5 = "test sms using java dekstop";
    static SerialPort serialPort;
    static OutputStream outputStream;
    static InputStream inputStream;
    static char enter = 13;
    static char CTRLZ = 26;

    public static void main(String[] args) throws InterruptedException {
//    System.loadLibraryPath("rxtxSerial");
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            System.out.println("msk while smssender");
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(selectedPort)) {
                    try {
                        serialPort = (SerialPort) portId.open("ttyUSB6", 2000);
                        outputStream = serialPort.getOutputStream();
                        inputStream = serialPort.getInputStream();
                        
                        serialPort.setSerialPortParams(115200,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (PortInUseException e) {
                        System.out.println("err");
                    } catch (IOException ex) {
                        Logger.getLogger(SMSsender.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedCommOperationException ex) {
                        Logger.getLogger(SMSsender.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {

                        sendSMS();

                        Thread.sleep(3000);

                        outputStream.close();
                        serialPort.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void sendSMS() {
        try {
            outputStream.write((messageString1 + enter).getBytes());
            Thread.sleep(100);
            outputStream.flush();

            outputStream.write((messageString3 + enter).getBytes());
            Thread.sleep(100);
            outputStream.flush();

            outputStream.write((messageString4 + enter).getBytes());
            Thread.sleep(100);
            outputStream.flush();

            outputStream.write((messageString5 + CTRLZ).getBytes());
            outputStream.flush();
            Thread.sleep(100);
        } catch (IOException ex) {
            Logger.getLogger(SMSPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SMSPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
