import sys
from cgi import escape

## code taken from https://www.rosettacode.org/wiki/CSV_to_HTML_translation

def _row2trextra(row, attr=None):
    cols = escape(row).split(',')
    attr_tr = attr.get('TR', '')
    attr_td = attr.get('TD', '')
    return (('<TR%s>' % attr_tr)
            + ''.join('<TD%s>%s</TD>' % (attr_td, data) for data in cols)
            + '</TR>')
 
def csv2htmlextra(txt, header=True, attr=None):
    ' attr is a dictionary mapping tags to attributes to add to that tag'
 
    attr_table = attr.get('TABLE', '')
    attr_thead = attr.get('THEAD', '')
    attr_tbody = attr.get('TBODY', '')
    htmltxt = '<TABLE%s>\n' % attr_table
    for rownum, row in enumerate(txt.split('\n')):
        htmlrow = _row2trextra(row, attr)
        rowclass = ('THEAD%s' % attr_thead) if (header and rownum == 0) else ('TBODY%s' % attr_tbody)
        htmlrow = '  <%s>%s</%s>\n' % (rowclass, htmlrow, rowclass[:5])
        htmltxt += htmlrow
    htmltxt += '</TABLE>\n'
    return htmltxt

# read csv file
title = sys.argv[2]
htmltxt = ''
with open(sys.argv[1]) as f:
    csvtxt = f.read()
    htmltxt = csv2htmlextra(csvtxt, True,
                            dict(TABLE=' border="1" summary="' + title + '"',
                                THEAD=' bgcolor="lightblue"',
                                TBODY=' bgcolor="lightgray"' 
                                )
                            )
    print(htmltxt)

with open(sys.argv[3], 'w') as fp:
    fp.write(htmltxt)