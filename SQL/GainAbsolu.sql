SELECT (
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer JOIN pokershand ON (jouer.PokerSHand_num = pokershand.num) 
WHERE Joueur_nom="Reathe" 
) as "Gain Total",

(
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[([1-9TJKQA])[shcd] \\1[shcd]\\]'
) as "Gain Total pocket pair",

(
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[[1-9TJKQA]([shcd]) [1-9TJKQA]\\1\\]'
) as "Gain Total suited hand",

(
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[([AKQJT])[shcd] \\1[shcd]\\]'
) as "Gain Total pocket pair T plus"
FROM DUAL;

