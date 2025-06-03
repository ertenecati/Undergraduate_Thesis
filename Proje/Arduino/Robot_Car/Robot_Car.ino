#include <AFMotor.h>

AF_DCMotor motor1 (1);  //Motor1 tanımlama
AF_DCMotor motor2 (2);  //Motor2 tanımlama
AF_DCMotor motor3 (3);  //Motor3 tanımlama
AF_DCMotor motor4 (4);  //Motor4 tanımlama

String voice;

void setup()
{
  Serial.begin(9600);   //baut rate hız ayarı
  motor1.setSpeed(200); //motor1 hız ayarı
  motor2.setSpeed(200); //motor2 hız ayarı
  motor3.setSpeed(200); //motor3 hız ayarı
  motor4.setSpeed(200); //motor4 hız ayarı

}

void loop()
{ if (Serial.available() > 0) {   //Bluetooth veri gelince if içerisine giriyor.
    while (Serial.available()) {  //gelen verileri Voice değişkenine sıralı atama yapıyor. 
      delay(10);

      char c = Serial.read();
      if (c == '#' || c == "2" || c == "8" || c == "4" || c == "6" || c == "5") {   //bluetoothtan veri gelmiyor ise döngüden çıkıyor.
        break;
      }
      voice += c;
    }
    if (voice.length() > 0) {     //komut arama ve komuta göre fonksiyon çağırma 
      if (voice == "*ileri git") {  //sesle ileri komutu
        moveForward();
      }
      else if (voice == "*geri dön") {  //sesle geri komutu
        moveBackward();
      }
      else if (voice == "*sola dön") {  //sesle sol komutu
        turnLeft();
      }
      else if (voice == "*sağa dön") {  //sesle sağ komutu
        turnRight();
      }
      else if (voice == "*dur") {   //sesle dur komutu
        moveStop();
      }
      else if (voice == "2") {    //ileri yön komutu
        moveForward();
      }
      else if (voice == "8") {    //geri yön komutu
        moveBackward();
      }
      else if (voice == "4") {    //sol yön komutu
        turnLeft();
      }
      else if (voice == "6") {    //sağ yön komutu
        turnRight();
      }
      else if (voice == "5") {    //dur yön komutu
        moveStop();
      }
      voice = "";
    }
  }
}

void moveForward() { //araç ileri git fonksiyonu

  motor1.run(FORWARD);
  motor2.run(FORWARD);
  motor3.run(FORWARD);
  motor4.run(FORWARD);
  delay(1000);

}

void moveBackward() { //araç geri git fonksiyonu

  motor1.run(BACKWARD);
  motor2.run(BACKWARD);
  motor3.run(BACKWARD);
  motor4.run(BACKWARD);
  delay(1000);

}

void turnRight()  //araç sağ dön fonksiyonu
{
  motor1.run(FORWARD);
  motor2.run(FORWARD);
  motor3.run(BACKWARD);
  motor4.run(BACKWARD);
  delay(800);
  motor1.run(RELEASE);
  motor2.run(RELEASE);
  motor3.run(RELEASE);
  motor4.run(RELEASE);
}

void turnLeft()  //araç sol dön fonksiyonu
{
  motor1.run(BACKWARD);
  motor2.run(BACKWARD);
  motor3.run(FORWARD);
  motor4.run(FORWARD);
  delay(800);
  motor1.run(RELEASE);
  motor2.run(RELEASE);
  motor3.run(RELEASE);
  motor4.run(RELEASE);
}

void moveStop() { // araç dur fonksiyonu

  motor1.run(RELEASE);
  motor2.run(RELEASE);
  motor3.run(RELEASE);
  motor4.run(RELEASE);
  delay(1000);
}
