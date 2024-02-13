class FoataNormalFormGenerator:
    @staticmethod
    def is_dependent(D, a, b):
        for dependence in D:
            if dependence[0] == a and dependence[1] == b:
                return True
        return False


    # Wyznaczenie postaci normalnej Foaty, FNF śladu [word]
    @staticmethod
    def get_foata_form(word, dependent_relations):
        FNF = [[]]
        FNF[0].append(word[0])

        for i in range(1, len(word)):
            curr = word[i]
            last_layer = True
            added = False

            for j in range(len(FNF) - 1, -1, -1):
                for char_in_current_fnf_layer in FNF[j]:
                    if FoataNormalFormGenerator.is_dependent(dependent_relations, curr, char_in_current_fnf_layer):
                        if last_layer:
                            FNF.append([curr])
                        else:
                            FNF[j + 1].append(curr)
                        added = True
                        break
                if added:
                    break
                last_layer = False
            if not added:
                FNF[0].append(curr)

        return FNF


    # Przekształcenie listowej reprezentacji FNF na string
    @staticmethod
    def foata_form_string(FNF):
        foata = ''
        for foata_class in FNF:
            foata += "(" + "".join(foata_class) + ")"
        return foata
