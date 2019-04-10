SELECT (
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer JOIN pokershand
WHERE Joueur_nom="Reathe" 
) as "Gain_Moyen",

(
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[([1-9TJKQA])[shcd] \\1[shcd]\\]'
) as "Gain_Moyen_pocket_pair",

(
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[[1-9TJKQA]([shcd]) [1-9TJKQA]\\1\\]'
) as "Gain_Moyen_suited_hand",

(
SELECT SUM(Gagne)-SUM(Mise)
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[([AKQJT])[shcd] \\1[shcd]\\]'
) as "Gain_Moyen_pocket_pair_T_plus"
FROM DUAL;

