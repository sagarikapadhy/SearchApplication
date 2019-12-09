<a href="https://www.google.com/imgres?imgurl=https%3A%2F%2Fpngriver.com%2Fwp-content%2Fuploads%2F2018%2F04%2FDownload-Search-Button-Png-Image-70612-For-Designing-Projects.png&imgrefurl=https%3A%2F%2Fpngriver.com%2Fdownload-search-button-png-image-70612-for-designing-projects-115283%2F&tbnid=Zk4W_dPY1uV0jM&vet=1&docid=FOviH7LdxeGAyM&w=512&h=512&itg=1&q=search%20application&source=sh%2Fx%2Fim"><img src="https://www.google.com/imgres?imgurl=https%3A%2F%2Fpngriver.com%2Fwp-content%2Fuploads%2F2018%2F04%2FDownload-Search-Button-Png-Image-70612-For-Designing-Projects.png&imgrefurl=https%3A%2F%2Fpngriver.com%2Fdownload-search-button-png-image-70612-for-designing-projects-115283%2F&tbnid=Zk4W_dPY1uV0jM&vet=1&docid=FOviH7LdxeGAyM&w=512&h=512&itg=1&q=search%20application&source=sh%2Fx%2Fim" title="SearchApplication" alt="SearchApplication"></a>





# Search Application Test

> software  that  receives  a  string  of  keywords,  and  a  string  URL.  This  is then  processed  to  return  a  string  of  numbers  for  where  the  resulting  URL  is  found  in  the  Google results. For  example,  “1,  10,  33”  or  “0”. 

> 

>  http://searchapplication-env.mzq5k9k6xu.us-east-2.elasticbeanstalk.com/




**How To run on AWS**

![Recordit GIF](http://g.recordit.co/he2fSQSz6V.gif)



## Table of Contents (Optional)

> If your `README` has a lot of info, section headers might be nice.

- [Installation](#installation)
- [Features](#features)
- [FAQ](#faq)
- [Support](#support)
- [License](#license)



---

## Installation

- Install apache tomcat server
- clone the app from git
- use any IDE to run it on tomcat server.

### Clone

- Clone this repo to your local machine using `https://github.com/sagarikapadhy/searchapplication.git`

### How To Use


> steps

```shell

Run the web app on your local or hit the cloud url.
Enter the keywords to be searched in comma separated(for multiple)
Enter the url to be parsed and count
Press Seach button
Result will be displayed in a pop up window.
```

> Sequence Algorithm

```shell
When a user runs the app or open the cloud url

If keyword field is not empty(){
   if url is not empty(){
      search would call a background mvc api call
      if multiple keyword with comma separated(){
         split each word.execute in a loop
            call doSearch()
            display result on screen
      }
            
      else
         call doSearch()
         display result on screen
   }
   display error message
}
display error message

doSearch(){
 
   Build google search url with inputs. search result max set to 100 per page.
   if success(){
     convert the html stream to string
     parse the string using regex.extract all the hrefs.
     regex again for the input url
     add to a list
     return size of list.
  }
      

```


- For all the possible languages that support syntax highlithing on GitHub (which is basically all of them), refer <a href="https://github.com/github/linguist/blob/master/lib/linguist/languages.yml" target="_blank">here</a>.

---

## Features
## Usage (Optional)
## Documentation (Optional)
## Tests (Optional)

- Going into more detail on code and technologies used
- `README`.

---

## Contributing

> To get started...

### Step 1

- **Option 1**
    - 🍴 Fork this repo!

- **Option 2**
    - 👯 Clone this repo to your local machine using `https://github.com/sagarikapadhy/searchapplication.git`

### Step 2

- **HACK AWAY!** 🔨🔨🔨

### Step 3

- 🔃 Create a new pull request .

---



## FAQ

- ****
    

---

## Support

Reach out to me at one of the following places!
Sagarika.Padhy478@gmail.con

---








- Copyright 2019 © <a href="https://github.com/sagarikapadhy/searchapplication.git" target="_blank">Sagarika</a>.
