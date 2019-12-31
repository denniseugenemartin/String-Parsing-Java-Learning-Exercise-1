# String-Parsing-Java-Learning-Exercise
Take input files and parse quotes from them and output into another file.

Level 1:
Go through a file and replace all sentences in quotes with a given phrase, such as "Dub-dub" For example:
"What should we do?" she asked.
He shrugged his shoulders. "We could go to the park. You have any ideas?"

Becomes:
"Dub-dub?" she asked.
He shrugged his shoulders. "Dub-dub. Dub-dub?"

Level 2:
As above, but now replace sentences with stock sentences from a given input file. Questions become random questions, other sentences become random sentences.
The above becomes:
"What time is it?" she asked.
He shrugged his shoulders. "Rabbits are everywhere this year. Can I borrow a hat?"

Level 3:
Like Level 2, but the random sentences come from sentences in quotes of some file, rather than from a custom input file. So, if you read the original file, "What should we do?" and "You have any ideas?" would get stored as potential questions to use when replacing, and "We could go to the park." would be stored as a potential statement to use for replacement.

Level 4:
Like Level 3, but avoid reusing sentences for as long as possible. If you ran the program with the same file to change as the file from which replacements came from, you'd basically get the dialogue to be shuffled.
