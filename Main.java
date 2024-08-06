import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useLocale(Locale.US);
            List<CSPlayer> players = new ArrayList<>();

            boolean addMorePlayers = true;
            while (addMorePlayers) {
                System.out.print("Enter player name: ");
                String name = scanner.nextLine();

                System.out.print("Enter player price: ");
                int price = scanner.nextInt();

                System.out.print("Enter player rating (last 3 months): ");
                double ratingLastThreeMonths = scanner.nextDouble();

                System.out.print("Enter player team ELO: ");
                int teamElo = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character                                                                                                                                                           

                players.add(new CSPlayer(name, price, ratingLastThreeMonths, teamElo));

                System.out.print("Do you want to add another player? (yes/no): ");
                String response = scanner.nextLine();
                addMorePlayers = response.equalsIgnoreCase("yes");
            }

            if (players.isEmpty()) {
                System.out.println("No players to evaluate.");
                return;
            }
    
            double maxPrice = Double.MIN_VALUE;
            double maxRating = Double.MIN_VALUE;
            int maxTeamElo = Integer.MIN_VALUE;

            for (CSPlayer player : players) {
                if (player.getPrice() > maxPrice) maxPrice = player.getPrice();
                if (player.getRating() > maxRating) maxRating = player.getRating();
                if (player.getTeamElo() > maxTeamElo) maxTeamElo = player.getTeamElo();
            }
            
            System.out.println("Evaluating players based on worthiness score (higher is better):\n");

            CSPlayer bestPlayer = null;
            double highestScore = Double.MIN_VALUE;
    
            for (CSPlayer player : players) {
                double score = CSPlayer.calculateWorthiness(player, maxPrice, maxRating, maxTeamElo);
                System.out.printf("%s: Worthiness Score = %.2f\n", player.getName(), score);
    
                if (score > highestScore) {
                    highestScore = score;
                    bestPlayer = player;
                }
            }
    
            if (bestPlayer != null) {
                System.out.printf("\nThe best player to pick is: %s with a worthiness score of %.2f\n", bestPlayer.getName(), highestScore);
            }
        }
    }
}