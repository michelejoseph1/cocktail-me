package com.example.cocktailme.models;

public class Constants {

    public static String[] fewRandomSuggestedText = {"Vodka", "Gin", "Rum", "Tequila", "Scotch",
            "Absolut Kurant", "Absolut Peppar", "Absolut Vodka", "Advocaat", "Aejo Rum",
            "Aftershock", "Agave Syrup", "Ale", "Allspice", "Almond Flavoring", "Almond", "Amaretto", "Angelica Root",
            "Angostura Bitters", "Anis", "Anise", "Anisette", "Aperol", "Apfelkorn", "Apple Brandy",
            "Apple Cider", "Apple Juice", "Apple Schnapps", "Apple", "Applejack", "Apricot Brandy", "Apricot Nectar",
            "Apricot", "Aquavit", "Asafoetida", "Añejo Rum", "Bacardi Limon", "Bacardi", "Baileys Irish Cream",
            "Banana Liqueur", "Banana Rum", "Banana Syrup", "Banana", "Barenjager", "Basil", "Beef Stock",
            "Beer", "Benedictine", "Berries", "Bitter lemon", "Bitters", "Black Pepper", "Black Rum", "Black Sambuca",
            "Blackberries", "Blackberry Brandy", "Blackberry Schnapps", "Blackcurrant Cordial", "Blackcurrant Schnapps",
            "Blackcurrant Squash", "Blended Whiskey", "Blue Curacao", "Blue Maui", "Blueberries", "Blueberry Scnapps"
            , "Bourbon", "Brandy", "Brown Sugar", "Butter", "Butterscotch Schnapps", "Cachaca", "Calvados",
            "Campari", "Canadian Whisky", "Candy", "Cantaloupe", "Caramel Coloring", "Carbonated Soft Drink", "Carbonated Water",
            "Cardamom", "Cayenne Pepper", "Celery Salt", "Celery", "Chambord Raspberry Liqueur", "Champagne",
            "Cherries", "Cherry Brandy", "Cherry Cola", "Cherry Grenadine", "Cherry Heering", "Cherry Juice",
            "Cherry Liqueur", "Cherry", "Chocolate Ice-cream", "Chocolate Liqueur", "Chocolate Milk", "Chocolate Syrup",
            "Chocolate", "Cider", "Cinnamon Schnapps", "Cinnamon", "Citrus Vodka", "Clamato Juice", "Cloves",
            "Club soda", "Coca-Cola", "Cocktail Onion", "Cocoa Powder", "Coconut Cream", "Coconut Liqueur", "Coconut Milk", "Coconut Rum",
            "Coconut Syrup", "Coffee Brandy", "Coffee Liqueur", "Coffee", "Cognac", "Cointreau", "Cola",
            "Cold Water", "Condensed Milk", "Coriander", "Corn Syrup", "Cornstarch", "Corona", "Cranberries",
            "Cranberry Juice", "Cranberry Liqueur", "Cranberry Vodka", "Cream of Coconut", "Cream Sherry",
            "Cream Soda", "Cream", "Creme De Almond", "Creme De Banane", "Creme De Cacao", "Creme De Cassis",
            "Creme De Noyaux", "Creme Fraiche", "Crown Royal", "Crystal Light", "Cucumber", "Cumin Powder",
            "Cumin Seed", "Curacao", "Cynar", "Daiquiri Mix", "Dark Chocolate", "Dark Creme De Cacao", "Dark Rum",
            "Dark Soy Sauce", "Demerara Sugar", "Dr. Pepper", "Drambuie", "Dried Oregano", "Dry Vermouth",
            "Dubonnet Blanc", "Dubonnet Rouge", "Egg White", "Egg Yolk", "Egg", "Eggnog", "Erin Cream",
            "Espresso", "Everclear", "Fanta", "Fennel Seeds", "Firewater", "Flaked Almonds", "Food Coloring",
            "Forbidden Fruit", "Frangelico", "Fresca", "Fresh Basil", "Fresh Lemon Juice", "Fruit Juice",
            "Fruit Punch", "Fruit", "Galliano", "Garlic Sauce", "Gatorade", "Ginger Ale", "Ginger Beer",
            "Ginger", "Glycerine", "Godiva Liqueur", "Gold rum", "Gold Tequila", "Goldschlager", "Grain Alcohol",
            "Grand Marnier", "Granulated Sugar", "Grape juice", "Grape soda", "Grapefruit Juice", "Grapes",
            "Green Chartreuse", "Green Creme de Menthe", "Green Ginger Wine", "Green Olives", "Grenadine",
            "Ground Ginger", "Guava juice", "Guinness stout", "Guinness", "Half-and-half", "Hawaiian punch",
            "Hazelnut liqueur", "Heavy cream", "Honey", "Hooch", "Hot Chocolate", "Hot Damn", "Hot Sauce",
            "Hpnotiq", "Ice-Cream", "Ice", "Iced tea", "Irish cream", "Irish Whiskey", "Jack Daniels",
            "Jello", "Jelly", "Jagermeister", "Jim Beam", "Johnnie Walker", "Kahlua", "Key Largo Schnapps",
            "Kirschwasser", "Kiwi liqueur", "Kiwi", "Kool-Aid", "Kummel", "Lager", "Lemon Juice",
            "Lemon Peel", "Lemon soda", "Lemon vodka", "Lemon-lime soda", "lemon-lime", "lemon", "Lemonade",
            "Licorice Root", "Light Cream", "Light Rum", "Lillet", "Lime juice cordial", "Lime Juice",
            "Lime liqueur", "Lime Peel", "Lime vodka", "Lime", "Limeade", "Madeira", "Malibu Rum", "Mandarin",
            "Mandarine napoleon", "Mango", "Maple syrup", "Maraschino cherry juice", "Maraschino Cherry",
            "Maraschino Liqueur", "Margarita mix", "Marjoram leaves", "Marshmallows", "Maui",
            "Melon Liqueur", "Melon Vodka", "Mezcal", "Midori Melon Liqueur", "Midori", "Milk", "Mint syrup",
            "Mint", "Mountain Dew", "Nutmeg", "Olive Oil", "Olive", "Onion", "Orange Bitters", "Orange Curacao",
            "Orange Juice", "Orange liqueur", "Orange Peel", "Orange rum","Orange Soda", "Orange spiral",
            "Orange vodka", "Orange", "Oreo cookie", "Orgeat Syrup", "Ouzo", "Oyster Sauce", "Papaya juice",
            "Papaya", "Parfait amour", "Passion fruit juice", "Passoa", "Peach brandy", "Peach juice",
            "Peach liqueur", "Peach Nectar", "Peach Schnapps", "Peach Vodka", "Peach", "Peachtree schnapps",
            "Peanut Oil", "Pepper", "Peppermint extract", "Pepsi Cola", "Pernod",
            "Peychaud bitters", "Pina colada mix","Pineapple Juice","Pineapple rum","Pineapple vodka",
            "Pineapple-orange-banana juice", "Pineapple", "Pink lemonade", "Pisang Ambon",
            "Pisco", "Plain Chocolate", "Plums", "Port", "Powdered Sugar", "Purple passion", "Raisins",
            "Raspberry cordial", "Raspberry Jam", "Raspberry Juice", "Raspberry Liqueur",
            "Raspberry schnapps", "Raspberry syrup", "Raspberry Vodka", "Red Chile Flakes",
            "Red Chili Flakes", "Red Hot Chili Flakes", "Red Wine", "Rhubarb", "Ricard", "Rock Salt",
            "Root beer schnapps", "Root beer", "Roses sweetened lime juice", "Rosewater", "Rumple Minze",
            "Rye Whiskey", "Sake", "Salt", "Sambuca","Sarsaparilla", "Schnapps", "Schweppes Lemon",
            "Schweppes Russchian", "Sherbet", "Sherry","Sirup of roses", "Sloe Gin", "Soda Water",
            "Sour Mix", "Soy Milk", "Soy Sauce", "Soya Milk", "Soya Sauce", "Spiced Rum", "Sprite",
            "Squeezed Orange", "Strawberries", "Strawberry juice", "Strawberry liqueur", "Strawberry Schnapps",
            "Sugar Syrup", "Sugar", "Sunny delight", "Surge", "Swedish punsch",
            "Sweet and Sour", "Sweet Cream", "Sweet Cream", "Sweet Vermouth", "Tabasco Sauce", "Tang",
            "Tawny port", "Tea", "Tennessee whiskey", "Tia Maria", "Tomato Juice", "Tomato", "Tonic Water",
            "Triple Sec", "Tropicana", "Vanilla extract", "Vanilla Ice-Cream", "Vanilla liqueur",
            "Vanilla schnapps", "Vanilla syrup", "Vanilla vodka", "Vanilla", "Vermouth", "Vinegar",
            "Water", "Watermelon schnapps", "Whipped Cream", "Whipping Cream", "White chocolate liqueur",
            "White Creme de Menthe", "White grape juice", "White port", "White Rum", "White Vinegar",
            "White Wine", "Wild Turkey", "Wildberry schnapps", "Wine", "Worcestershire Sauce", "Yeast",
            "Yellow Chartreuse", "Yoghurt", "Yukon Jack", "Zima", "Caramel Sauce", "Chocolate Sauce",
            "Lillet Blanc", "Peach Bitters", "Mini-snickers bars", "Prosecco", "Salted Chocolate", "Martini Rosso",
            "Martini Bianco", "Martini Extra Dry", "Fresh Lime Juice","Fresh Mint", "Rosemary", "Habanero Peppers",
            "Ilegal Joven mezcal", "Elderflower cordial","Rosso Vermouth", "Creme de Violette", "Cocchi Americano",
            "White Vermouth",  "Dry Curacao", "Nocino", "Averna","Ramazzotti","Fernet-Branca","Allspice Dram",
            "Falernum","Singani","Arrack","Blackstrap rum","Ginger Syrup","Honey syrup","Blended Scotch",
            "Islay single malt Scotch","151 proof rum","7-up","Absinthe","Absolut citron", "Creme de Mure",
            "Olive Brine","Pineapple Syrup","St. Germain","Lavender","Whiskey","Whisky", "Pomegranate juice",
            "Watermelon","Chareau","Cinnamon Whisky","Red Bull","Diet Coke", "Rosemary Syrup","Figs", "Thyme",
            "Blood Orange","Amaro Montenegro","Ruby Port","Rose"

    };
}