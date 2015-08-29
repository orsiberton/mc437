
Scenario: Looking up the definition of '<word>'
Given the user is on the Wikionary home page
When the user looks up the definition of the word '<word>'
Then they should see the definition '<definition>'

Examples:
| word    | definition                                                                                      | 
| apple   | A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.   | 
| pear    | An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.  | 
| bruno   | (dark) brown                                                                                    |
