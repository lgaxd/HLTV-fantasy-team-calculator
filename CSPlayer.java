public class CSPlayer {
    
    private final String name;
    private final int price;
    private final double ratingThreeMonths;
    private final int teamElo;
    
    public CSPlayer (String name, int price, double ratingThreeMonths, int teamElo) {
        
        this.name = name;
        this.price = price;
        this.ratingThreeMonths = ratingThreeMonths;
        this.teamElo = teamElo;
        
    }
    
    public String getName() {
        
        return name;
        
    }
    
    public int getPrice() {
        
        return price;
        
    }
    
    public double getRating() {
        
        return ratingThreeMonths;
        
    }
    
    public int getTeamElo() {
        
        return teamElo;
        
    }
    
    @Override
    public String toString() {
        return String.format("%s - Price: %.2f, Rating: %.2f, Team ELO: %d", name, (float) price, ratingThreeMonths, teamElo);
    }
    
    public static double calculateWorthiness(CSPlayer player, double maxPrice, double maxRating, int maxTeamElo) {
        double priceScore = (maxPrice - player.getPrice()) / maxPrice;
        double ratingScore = player.getRating() / maxRating;
        double teamEloScore = (double) player.getTeamElo() / maxTeamElo;

        return (priceScore + ratingScore + teamEloScore) / 3;
    }
    
}