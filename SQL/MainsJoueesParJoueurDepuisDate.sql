SELECT *
FROM jouer LEFT JOIN pokershand ON (jouer.PokerSHand_num = pokershand.num) 
WHERE Joueur_nom="Reathe" AND LaDate >= '2019-04-08 10:00:00'