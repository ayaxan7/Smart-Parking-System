int a = 8;
int b;
int c = 9;
int d;
int e = 10;
int f;

void setup() {
  pinMode(a, INPUT);
  pinMode(c, INPUT);
  pinMode(e, INPUT);
  Serial.begin(9600);
  
}

void loop() {
  b = digitalRead(a);
  d = digitalRead(c);
  f = digitalRead(e);
  
  
  Serial.print(b);
  Serial.print(",");
  Serial.print(d);
  Serial.print(",");
  Serial.println(f);
  
  delay(2000);
}
