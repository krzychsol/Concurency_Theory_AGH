from dependency_calculator import DependencyCalculator
from foata_normal_form_generator import FoataNormalFormGenerator
from graph_generator import GraphGenerator

# Wczytanie pliku wejściowego
def read_input_file(file_path):
    encodings_to_try = ['utf-8', 'latin-1', 'cp1250']
    for encoding in encodings_to_try:
        try:
            with open(file_path, 'r', encoding=encoding) as file:
                lines = file.readlines()
            return [line.strip() for line in lines]
        except UnicodeDecodeError:
            print(f"Failed to decode using {encoding} encoding. Trying another encoding...")
    raise ValueError("Unable to determine the correct encoding.")


# Preprocessing danych wejściowych
def preprocess_data(input_data):
    alphabet = set()
    word = ""
    transactions = []

    for line in input_data:
        # Getting operation result and operation itself
        if ":=" in line:
            index = line.index(":=")
            symbol, result, operation = line[index - 5], line[index - 2], line[index + 2:].strip()
            transactions.append([symbol, result, operation])
        # Getting alphabet
        if line.startswith('A'):
            alphabet.update(char for char in line.split('{', 1)[-1].rsplit('}', 1)[0] if char.isalpha())
        # Getting word
        if line.startswith('w'):
            word = line.split('=')[1].strip()

    alphabet = sorted(alphabet)
    return alphabet, word, transactions


def run(alphabet, dependency_relations, word):
    print("Alphabet: ", sorted(alphabet))
    print("word: ", word)
    I, D = dependency_relations.calculate_dependencies()
    print("I: ", sorted(I, key=lambda x: x[0]))
    print("D: ", sorted(D, key=lambda x: x[0]))
    FNF = FoataNormalFormGenerator.get_foata_form(word, D)
    print(FoataNormalFormGenerator.foata_form_string(FNF))


if __name__ == "__main__":
    file_path = "example_input_file3.txt"
    input_data = read_input_file(file_path)

    # Preprocessing danych wejściowych
    alphabet, word, transactions = preprocess_data(input_data)
    dependency_calculator = DependencyCalculator(transactions)

    # Obliczenie FNF i wyświetlenie
    run(alphabet, dependency_calculator, word)

    # Generowanie pliku .dot reprezentującego graf Diekerta
    GraphGenerator.generate_graph(word, dependency_calculator.D)
