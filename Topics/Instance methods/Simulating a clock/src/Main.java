
class Clock {

    int hours = 12;
    int minutes = 0;

    public void next() {
        if (this.hours == 12 && this.minutes == 59) {
            this.hours = 1;
            this.minutes = 0;
        } else if (minutes == 59) {
            this.hours++;
            this.minutes = 0;
        } else {
            this.minutes++;
        }
    }
}