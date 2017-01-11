#include <SoftwareSerial.h>

SoftwareSerial BT(3,4);
int trig = 8;
int echo =9;
int buzzer = 2;
int check=0;
int del = 300;
int duration = 2000;

void setup()
{
 Serial.begin(9600);
 pinMode(trig, OUTPUT);
 pinMode(echo,INPUT);
 
 BT.begin(9600);
 
 Serial.println("Hello from Arduino");
}
void loop()
{
  
  char cmd;
  digitalWrite(trig,HIGH);
  delayMicroseconds(10);
  digitalWrite(trig,LOW);
  int distance = pulseIn(echo,HIGH)*17/1000;

 if(BT.available()){

    cmd=(char)BT.read();
    Serial.println(cmd);
  }
  if(Serial.available()){
    BT.write(Serial.read());
    
  }
  if(cmd=='1'){
    noTone(buzzer);
    check=1;
  }
  if(cmd=='2'){
    check = 2;
  }
  if(cmd=='0' ){
    tone(buzzer,1800,duration);
    delay(del);
    
  }
  BT.println(distance);
  delay(1000);
}

