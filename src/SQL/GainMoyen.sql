SELECT (
SELECT AVG(Gagne)-AVG(Mise) as "Gain Moyen"
FROM jouer
WHERE Joueur_nom="Reathe")
as "Gain Moyen",

(
SELECT AVG(Gagne)-AVG(Mise) as "Gain Moyen pocket pair"
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[([1-9TJKQA])[shcd] \\1[shcd]\\]'
) as "Gain Moyen pocket pair",

(
SELECT AVG(Gagne)-AVG(Mise) as "Gain Moyen suited hand"
FROM jouer
WHERE Joueur_nom="Reathe" AND Main REGEXP '\\[[1-9TJKQA]([shcd]) [1-9TJKQA]\\1\\]'
) as "Gain Moyen suited hand"
