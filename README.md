 

# Search Application Test

> software  that  receives  a  string  of  keywords,  and  a  string  URL.  This  is then  processed  to  return  a  string  of  numbers  for  where  the  resulting  URL  is  found  in  the  Google results. For  example,  “1,  10,  33”  or  “0”. 

> 

>  http://searchapplication-env.mzq5k9k6xu.us-east-2.elasticbeanstalk.com/


**Application demo**

![Recordit GIF](http://g.recordit.co/E0FriUGC7Q.gif)

**How To run on AWS**
Make sure access to AWS console. 

![Recordit GIF](http://g.recordit.co/he2fSQSz6V.gif)



## Table of Contents (Optional)

> If your `README` has a lot of info, section headers might be nice.

- [Installation](#installation)
- [Clone](#clone)
- [HowToUse](#howtouse)
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

### HowToUse


> steps

```shell

> Run the web app on your local or hit the cloud url.
> Enter the keywords to be searched in comma separated(for multiple)
> Enter the url to be parsed and count
> Press Seach button
> Result will be displayed in a pop up window.
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

## Documentation

- Going into more detail on code and technologies used
- `README`.


## Tests 

```shell

> Used Junit and mockito framework for unit test.
> Selenium can be used for UI level testing. Need more time.
> jscoup third party library can be used to verify result with regex impl.

```



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


## Support

Reach out to me at one of the following places!
Sagarika.Padhy478@gmail.con

---








- Copyright 2019 © <a href="https://github.com/sagarikapadhy/searchapplication.git" target="_blank">Sagarika</a>.
