import os, sys, json, re

def spellcheck(mdfile, dictionary, regex):
    badWords = []

    # print("Spell checking file " + mdfile + " using dictionary " + dictionary)

    # run pandoc to generate HTML
    # pandoc ./design-document/_posts/2019-07-26-Requirements.markdown -f markdown -t html -s -o req.html
    pandocCmd = 'pandoc ' + mdfile + ' -f markdown -t html -s -o tmp.html'
    os.system(pandocCmd)
    
    # run hunspell
    hunspellCmd = 'hunspell -H -l tmp.html'
    hunspellOut = os.popen(hunspellCmd).read()

    #clean up temp file
    os.system('rm tmp.html')

    # remove hunspell items that are in our dictionary
    # print("dictionary words: " + str(dictionary))

    for word in hunspellOut.splitlines():
        # print("Checking dictionary for: " + word)
        if word not in dictionary:
            regexMatch = False
            for regexEntry in regex:
                if re.match(regexEntry, word):
                    regexMatch = True
                    # print("Match " + regexEntry + " for " + word)
                    continue
            if not regexMatch:        
                badWords.append(word)
        else:
            # print("Found " + word + " in dictionary")
            continue

    # return list of misspelled words
    return badWords

# print ("check spelling for markdown in " + sys.argv[2] + " using dictionary " + sys.argv[1])
dictionary = [] 
regex = []
spellingErrors = {}

with open(sys.argv[1]) as f:
        dictionaryContent = f.read().splitlines()
        for entry in dictionaryContent:
            if entry.startswith("re="):
                regex.append(entry.split("=")[1])
            else:
                dictionary.append(entry)


with os.scandir(sys.argv[2]) as it:
    for entry in it:
        if entry.path.endswith(".md") or entry.path.endswith(".markdown"):
            # do spell checking
            misspelledWords = spellcheck(entry.path, dictionary, regex)
            if len(misspelledWords) == 0:
                continue
            else:
                # keep track of words and files
                spellingErrors[entry.path] = misspelledWords

# if spelling errors exist, print and write to file
if not spellingErrors:
    print(json.dumps(spellingErrors))

    if  sys.argv[3]:
        with open(sys.argv[3], 'w') as fp:
            json.dump(spellingErrors, fp)


