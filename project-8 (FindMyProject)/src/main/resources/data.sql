INSERT INTO RESEARCHER(name, specialization) VALUES
('Marie Curie', 'Radioactivity'),
('Albert Einstein', 'Relativity'),
('Isaac Newton', 'Classical Mechanics'),
('Niels Bohr', 'Quantum Mechanics');

INSERT INTO PROJECT(name, budget) VALUES
('Project Alpha', 50000.00),
('Project Beta', 100000.00),
('Project Gamme', 150000.00),
('Project Delta', 75000.00);

INSERT INTO RESEARCHER_PROJECT(researcherId, projectId) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(3, 4),
(4, 4);