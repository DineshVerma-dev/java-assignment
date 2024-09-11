public class TrainTimetableFullDay {

    public static void main(String[] args) {
        // Define parameters
        int startHour = 7;   // Trains start at 7:00 AM
        int endHour = 20;    // Trains end at 8:00 PM
        int trainFrequency = 10;  // Frequency in minutes
        int totalStations = 10;  // Number of stations between source and destination
        int travelTimeBetweenStations = 4;  // Travel time between stations (in minutes)
        int waitTimeAtDestination = 20;  // Wait time at destination (in minutes)

       
        generateTrainSchedule(startHour, endHour, trainFrequency, totalStations, travelTimeBetweenStations, waitTimeAtDestination);
    }

   
    public static void generateTrainSchedule(int startHour, int endHour, int frequency, int totalStations, int travelTime, int waitAtDestination) {
       
        for (int hour = startHour; hour < endHour; hour++) {
            for (int minute = 0; minute < 60; minute += frequency) {
                
                System.out.println("Train departing at: " + formatTime(hour, minute));

               
                int currentMinute = minute;
                int currentHour = hour;

             
                for (int station = 1; station <= totalStations; station++) {
                    currentMinute += travelTime;

                    if (currentMinute >= 60) {
                        currentHour++;
                        currentMinute -= 60;
                    }

                    if (currentHour >= endHour && currentMinute >= 0) {
                        break;
                    }

                    System.out.println("Arriving at Station " + station + ": " + formatTime(currentHour, currentMinute));
                }

               
                System.out.println("Arriving at destination: " + formatTime(currentHour, currentMinute));

               
                currentMinute += waitAtDestination;
                if (currentMinute >= 60) {
                    currentHour++;
                    currentMinute -= 60;
                }

                
                System.out.println("Departing from destination: " + formatTime(currentHour, currentMinute));

                for (int station = totalStations; station >= 1; station--) {
                    currentMinute += travelTime;

                    if (currentMinute >= 60) {
                        currentHour++;
                        currentMinute -= 60;
                    }

                    if (currentHour >= endHour && currentMinute >= 0) {
                        break;
                    }

                    System.out.println("Arriving at Station " + station + ": " + formatTime(currentHour, currentMinute));
                }

               
                System.out.println("Arriving back at source: " + formatTime(currentHour, currentMinute));
                System.out.println();  // Blank line for separation
            }
        }
    }

   
    public static String formatTime(int hour, int minute) {
        String amPm = (hour >= 12) ? "PM" : "AM";
        hour = (hour > 12) ? hour - 12 : (hour == 0) ? 12 : hour;  // Convert to 12-hour format

        // Ensure minutes are always two digits (e.g., 07:05)
        return String.format("%02d:%02d %s", hour, minute, amPm);
    }
}
