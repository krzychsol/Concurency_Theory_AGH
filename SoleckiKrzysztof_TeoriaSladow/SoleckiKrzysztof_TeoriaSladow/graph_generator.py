from foata_normal_form_generator import FoataNormalFormGenerator
from graph_connection import GraphConnection
from node import Node


class GraphGenerator:
    @staticmethod
    def generate_graph(word, dependent_relations):
        nodes = []
        previous_nodes = []

        for i, curr in enumerate(word):
            nodes.append(Node(curr, i))

        connections = []
        for node in nodes:
            for previous_node in previous_nodes:
                if FoataNormalFormGenerator.is_dependent(dependent_relations, node.value, previous_node.value) and not node.check_if_connected(previous_node, dependent_relations):
                    node.add_connection(previous_node)
                    connections.append(GraphConnection(previous_node, node))
            previous_nodes.insert(0, node)

        with open('./output_graph.dot', 'w') as file:
            file.write("digraph G {\n")
            for connection in connections:
                file.write(f"  {connection.source.index} -> {connection.target.index};\n")

            labels = ";".join(f"{node.index} [label={node.value}]" for node in nodes)
            file.write(f"  {labels};\n")
            file.write("}")