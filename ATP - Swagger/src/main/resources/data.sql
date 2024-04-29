

INSERT INTO tenistas (id,ranking, nombre_completo, pais, fecha_nacimiento, altura, peso, debut_profesional, mano, reves, entrenador, dinero_ganado, mejor_ranking, num_victorias, num_derrotas, imagen, edad, puntos) VALUES
                                                                                                                                                                                                  (1,1, 'Cristiano Ronaldo', 'Portugal', '1985-02-05', 185, 80, '2008-07-01', 0, 0, 'Cristiano Ronaldo', 10000000, 1, 0, 0, 'ronaldo.jpg', 25, 1),
                                                                                                                                                                                                  (2,2, 'Lionel Messi', 'Argentina', '1987-06-24', 170, 60, '2005-06-24', 1, 1, 'Lionel Messi', 5000000, 2, 0, 0, 'messi.jpg', 26,2),
                                                                                                                                                                                                  (3,3, 'Neymar', 'Brasil', '1992-02-05', 175, 68, '2012-02-05', 0, 0, 'Neymar', 3000000, 3, 0, 0, 'neymar.jpg', 27, 3),
                                                                                                                                                                                                  (4,4, 'Kylian Mbappe', 'France', '1998-12-20', 180, 75, '2018-12-20', 1, 1, 'Kylian Mbappe', 2000000, 4, 0, 0, 'mbappe.jpg', 28, 4),
                                                                                                                                                                                                  (5,5, 'Mohamed Salah', 'Egypt', '1992-06-15', 175, 68, '2012-06-15', 0, 0, 'Mohamed Salah', 1000000, 5, 0, 0, 'salah.jpg', 29, 5),
                                                                                                                                                                                                  (6,6, 'Hugo Lloris', 'Belgium', '1986-12-26', 180, 80, '2006-12-26', 0, 1, 'Hugo Lloris', 500000, 6, 0, 0, 'lloris.jpg', 30, 6);

INSERT INTO torneos(id,ubicacion, tipo, categoria, modalidad, superficie, vacantes, premios, fecha_inicio, fecha_fin, imagen) VALUES
                                                                                                                     (1,'Madrid', 0,0, 0, 0, 64, '$500,000', '2024-04-01', '2024-04-07', 'Torneo1.png'),
                                                                                                                     (2,'París', 0, 0, 0, 0, 32, '$750,000', '2024-05-15', '2024-05-21', 'Torneo2.png'),
                                                                                                                     (3,'Londres', 0, 0, 0, 0, 128, '$1,000,000', '2024-06-10', '2024-06-16', 'Torneo3.png'),
                                                                                                                     (4,'Nueva York', 0, 0, 0, 0, 64, '$1,500,000', '2024-08-28', '2024-09-10','Torneo4.png'),
                                                                                                                     (5,'Barcelona', 0, 0, 0, 0, 32, '$750,000', '2024-09-23', '2024-09-29', 'Torneo5.png');

