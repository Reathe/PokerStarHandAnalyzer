SELECT 1-(nbNotFold.nb / TotalMain.nb) as PourcentageCouche
FROM (
    SELECT COUNT(PokerSHand_num) as nb
    FROM Jouer 
    WHERE Joueur_nom='Reathe' and (Action = 'mucked' or Action = "showed" or Action = 'collected')
    ) as nbNotFold,
    (
    select count(PokerSHand_num) as nb
    FROM Jouer 
    Where Joueur_nom = "Reathe"
    ) as TotalMain