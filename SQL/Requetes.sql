-- Select rows from a Table or View 'jouer' in schema 'PokerDB'
SELECT *
FROM PokerDB.jouer
WHERE Joueur_nom = "Reathe"

-- Une Paire
-- main REGEXP '\\[([1-9TJKQA])[shcd] \\1[shcd]\\]'

-- Suited
 -- main REGEXP '\\[[1-9TJKQA]([shcd]) [1-9TJKQA]\\1\\]'