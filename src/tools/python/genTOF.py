import os, sys
# generates a table of figures and places it into the _includes directory

section = {}

# parse the markdown files, looking for figures (assume standard formatting)
with os.scandir(sys.argv[1]) as it:
    for entry in it:
        if entry.path.endswith(".md") or entry.path.endswith(".markdown"):
            #search for teh standard figure block within the file
            with open(entry.path) as f:
                content = f.readlines()
                section_num = ''
                figure_id = ''
                caption = ''
                figure_ids = []
                in_figure_block = False
                for line in content:
                    if "num:" in line:
                        section_num = line.split(":")[1].strip()
                        continue
                    if "{% include figure.html" in line:
                        in_figure_block = True
                        continue
                    if in_figure_block:
                        if "figure-id" in line:
                            #save figure id
                            # figure-id="Figure 2.0-1"
                            figure_id = line.split('=')[1].strip().lstrip('\"').rstrip('\"')
                            continue
                        if "caption" in line:
                            #save figure caption
                            # caption="The traditional Magic 8 Ball. Does this wrap?"
                            caption = line.split('=')[1].strip().lstrip('\"').rstrip('\"')
                            dataTuple = (figure_id, caption)
                            if section_num in section.keys():
                                section[section_num].append(dataTuple)
                            else:
                                section[section_num] = [dataTuple]

                            #reset values to empty
                            figure_id = ''
                            caption = ''
                            in_figure_block = False
                            continue
                # end for each line
            # end open file
        # end if markdown file
    # end for each file
# end with scandir

#write the tof.html file
out = "<ul>\n"
for key in sorted (section.keys()):
    data = section[key]
    for entry in data:
        out += "\t<li><a href=\"#" + str(entry[0]).strip() + "\">" + \
            str(entry[0]).strip() + ": " + str(entry[1]).strip() + "</a></li>\n"
out += "</ul>\n"

print(out)

if  sys.argv[3]:
    with open(sys.argv[3], 'w') as fp:
        fp.write(out)