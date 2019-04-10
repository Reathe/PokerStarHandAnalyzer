SELECT (
SELECT AVG(Gagne)-AVG(Mise)
FROM jouer LEFT JOIN pokershand ON (jouer.PokerSHand_num = pokershand.num) 
WHERE Joueur_nom="Reathe" AND LaDate >= '2019-04-08 10:00:00'
)
as "Gain Moyen",

(
SELECT AVG(Gagne)-AVG(Mise)
FROM jouer LEFT JOIN pokershand ON (jouer.PokerSHand_num = pokershand.num)
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[([1-9TJKQA])[shcd] \\1[shcd]\\]' AND LaDate >= '2019-04-08 10:00:00'
) as "Gain_Moyen_pocket_pair",

(
SELECT AVG(Gagne)-AVG(Mise)
FROM jouer LEFT JOIN pokershand ON (jouer.PokerSHand_num = pokershand.num)
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[[1-9TJKQA]([shcd]) [1-9TJKQA]\\1\\]' AND LaDate >= '2019-04-08 10:00:00'
) as "Gain_Moyen_suited_hand",

(
SELECT AVG(Gagne)-AVG(Mise)
FROM jouer LEFT JOIN pokershand ON (jouer.PokerSHand_num = pokershand.num)
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[([AKQJT])[shcd] \\1[shcd]\\]' AND LaDate >= '2019-04-08 10:00:00'
) as "Gain_Moyen_pocket_pair_T_plus"
FROM DUAL