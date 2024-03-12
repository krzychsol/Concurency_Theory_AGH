class DependencyCalculator:
    def __init__(self, transactions):
        self.transactions = transactions
        self.I = []
        self.D = []

    # Wyznaczenie relacji zależności i niezależności - I i D
    def calculate_dependencies(self):
        I = []
        D = []

        for entry_key, left, right in self.transactions:
            for neighbour_key, neighbour_left, neighbour_right in self.transactions:
                if neighbour_left == left:
                    D.append([entry_key, neighbour_key])
                elif left in neighbour_right:
                    D.append([entry_key, neighbour_key])
                elif neighbour_left in right:
                    D.append([entry_key, neighbour_key])
                else:
                    I.append([entry_key, neighbour_key])

        self.D = D
        self.I = I
        return I, D