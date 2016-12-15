# SMSGateway
Learn how to build Java Dekstop App for SMS gateway using GSM modem

## Getting Started

These app send bulk SMS using GSM Modem (tested using Wavecom and Syscom MP8P) 
in this project there is 3 main class:
- SMSPanel (the Main Interface)
- SMSSender (class that contain methods for send SMS)
- SMSCommand (class that contain AT-COMMANDs for send command to modem)

### Code

this app built using RXTXcomm lib to connect java app with GSM Modem (lib included in project).
first, it will identified all communication port, then open selected port
the serial port will receive command (AT COMMAND) via OutputStream and InputStream 

get all communication port using this code

```
portList = CommPortIdentifier.getPortIdentifiers();
```

set modem baud rate, databits, stopbits, and parity using this code

```
serialPort.setSerialPortParams(115200,
    SerialPort.DATABITS_8,
    SerialPort.STOPBITS_1,
    SerialPort.PARITY_NONE);
```

to send single SMS, use this simple raw AT COMMAND 

```
AT
AT+CMGF=1
AT+CMGS="+31638740161" //replace with recipient number

```
if you using netbeans, chang run setting by right click on project -> properties -> Run. in VM Options add this -Djava.library.path=/usr/lib/jni then click OK
<br /><br />
if you can't detect port then the port might be not granted permission, use this command in terminal
```
sudo chmod 666 /dev/ttyUSB0

```
or change /dev/ttyUSB0 with your connected port

### Useful Tutorial
- Minicom Installation and Configuration (Linux)
https://www.cyberciti.biz/tips/connect-soekris-single-board-computer-using-minicom.html

- HyperTerminal Installation and Configuration (Windows)
http://www.hilgraeve.com/hyperterminal-serial-port/

- AT COMMAND for SMS
http://www.smssolutions.net/tutorials/gsm/sendsmsat/

## Built With

* IDE - Netbeans 8.2
* Operating System - Ubuntu

## Authors

* **Marchellin Antonia**

## Screenshot
* panel to send sms <br />
<img src="https://cloud.githubusercontent.com/assets/12492522/21212439/3fe0c758-c2bf-11e6-8c3f-5db5e2c56390.png" width="500">
<br />
* panel to check inbox <br />
<img src="https://cloud.githubusercontent.com/assets/12492522/21212440/3fe1b88e-c2bf-11e6-9169-5274eaf7794b.png" width="500">
<br />
* panel to check outbox <br />
<img src="https://cloud.githubusercontent.com/assets/12492522/21212438/3fe09c42-c2bf-11e6-96ed-41d3bbc5bb30.png" width="500">
<br />
