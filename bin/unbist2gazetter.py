#!/usr/bin/env python3
import argparse
import rdflib.plugins.sparql
from rdflib.namespace import NamespaceManager

oparser = argparse.ArgumentParser(description="consolidate CVS output to one line per document",
                                  formatter_class=argparse.ArgumentDefaultsHelpFormatter)

oparser.add_argument("-i", dest="input_file",
                     required=True,
                     metavar="FILE", type=str,
                     help="input file")

oparser.add_argument("-o", dest="output_file",
                     required=True,
                     metavar="FILE", type=str,
                     help="output file")

options = oparser.parse_args()

graph = rdflib.Graph()
ns_manager = NamespaceManager(rdflib.Graph())
ns_manager.bind('skos', rdflib.namespace.SKOS)
graph.namespace_manager = ns_manager

graph.parse(options.input_file, format='n3')

print('Triples:', len(graph))

prefix_string = 'PREFIX skos: <%s>' % rdflib.namespace.SKOS

pref_label_query = '''PREFIX skos: <http://www.w3.org/2004/02/skos/core#> 
SELECT ?label 
WHERE { 
   <%s> skos:prefLabel ?label . 
   FILTER (lang(?label) = "en") 
}'''

alt_label_query = '''PREFIX skos: <http://www.w3.org/2004/02/skos/core#> 
SELECT ?label 
WHERE { 
   <%s> skos:altLabel ?label . 
   FILTER (lang(?label) = "en") 
}'''


query = rdflib.plugins.sparql.prepareQuery('%s SELECT DISTINCT ?x WHERE {?x a skos:Concept}' % prefix_string)

with open(options.output_file, 'w', encoding='utf-8') as f:
    for row in graph.query(query):
        concept = row[0]
        query1 = rdflib.plugins.sparql.prepareQuery(pref_label_query % concept)
        for row1 in graph.query(query1):
            pref_label = row1[0]
            continue
        f.write('\t'.join([pref_label, 'pref_label=%s' % pref_label, 'uri=%s' % concept]))

        query2 = rdflib.plugins.sparql.prepareQuery(alt_label_query % concept)
        for row2 in graph.query(query2):
            label = row2[0]
            f.write('\t'.join([label, 'pref_label=%s' % pref_label, 'uri=%s' % concept]))

