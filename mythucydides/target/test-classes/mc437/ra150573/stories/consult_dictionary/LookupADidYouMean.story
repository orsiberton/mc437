
Scenario: Looking up the definition of a wrong spelled '<word>'
Given the user is on the Wikionary home page
When the user looks up the definition of the word '<word>'
Then they should see the tip '<didYouMean>'

Examples:
| word    | didYouMean           |
| peear   | Did you mean: pearl  |
| aple    | Did you mean: apple  |
| bananna | Did you mean: banana |