def is_dependent(a, b, D):
    for dependence in D:
        if dependence[0] == a and dependence[1] == b:
            return True
    return False


class Node:
    def __init__(self, value, index):
        self.value = value
        self.index = index
        self.connections = []

    def add_connection(self, node):
        self.connections.append(node)

    def check_if_connected(self, node, dependent_relations):
        for connection in self.connections:
            if is_dependent(node.value, connection.value, dependent_relations):
                return True
        return False
