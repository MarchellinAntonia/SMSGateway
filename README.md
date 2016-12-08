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
